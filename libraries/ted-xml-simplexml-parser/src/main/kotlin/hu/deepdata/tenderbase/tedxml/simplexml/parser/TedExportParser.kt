/*
 *    Copyright 2018 DeepData Ltd.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package hu.deepdata.tenderbase.tedxml.simplexml.parser

import hu.deepdata.tenderbase.tedxml.simplexml.model.*
import mu.*
import org.simpleframework.xml.convert.*
import org.simpleframework.xml.core.*
import java.io.*
import java.net.*

/**
 * @author Zsolt Jurányi
 */
open class TedExportParser(var tedExport: TedExport? = null) {

	companion object : KLogging()

	protected var persister: Persister = Persister(AnnotationStrategy())

	fun parse(inputStream: InputStream): TedExportParser {
		inputStream.use {
			tedExport = persister.read(TedExport::class.java, inputStream, false)
			tedExport?.apply { tenderId = tenderId() }
		}
		return this
	}

	fun parse(file: File): TedExportParser {
		return parse(file.inputStream())
	}

	fun parse(resourceName: String): TedExportParser {
		return parse(ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName))
	}

	fun awardContracts(): List<AwardContract> {
		var awards = form(LanguageValue.EN)?.awardContracts ?: listOf()
		if (awards.isEmpty()) awards = formOriginal()?.awardContracts ?: listOf()
		return awards
	}

	fun awardValue(awardedContract: AwardedContract?) = listOfNotNull(
			awardedContract?.valTotal?.asValueRange(),
			awardedContract?.valueAlt?.valTotal?.asValueRange(),
			awardedContract?.valRangeTotal,
			awardedContract?.valueAlt?.valRangeTotal,
			awardedContract?.valEstimatedTotal?.asValueRange(),
			awardedContract?.valueAlt?.valEstimatedTotal?.asValueRange()
	).firstOrNull()

	fun buyer() = listOfNotNull(
			buyerTransliterated(),
			buyer(form(LanguageValue.EN)),
			buyer(formOriginal())
	).firstOrNull()

	fun buyer(form: Form?) = form?.contractingBody?.addressContractingBody

	fun buyerName(languageValue: LanguageValue) = tedExport!!.translationSection.mlAaNames
			.filter { it.lg == languageValue }
			.map(AaName::value)
			.first()

	fun buyerTransliterated() = tedExport!!.translationSection.transliteratedAddr

	fun codif() = tedExport!!.codedDataSection.codifData

	fun form(languageValue: LanguageValue) = tedExport!!.formSection.forms.firstOrNull { it.lg == languageValue }

	fun formOriginal() = tedExport!!.formSection.forms.firstOrNull { it.category == Category.ORIGINAL }

	fun get() = tedExport

	fun language() = tedExport!!.codedDataSection.noticeData.lgOrig.joinToString(",")

	fun lefti() = listOfNotNull(form(LanguageValue.EN)?.lefti, formOriginal()?.lefti).firstOrNull()

	fun objects(): List<ObjectContract> {
		var objects = form(LanguageValue.EN)?.objectContracts ?: listOf()
		if (objects.isEmpty()) objects = formOriginal()?.objectContracts ?: listOf()
		return objects
	}

	fun procedure() = listOfNotNull(form(LanguageValue.EN)?.procedure, formOriginal()?.procedure).firstOrNull()

	fun publicationDate() = tedExport!!.codedDataSection.refOjs.datePub

	fun refNoticeIds(): List<String> = tedExport!!.codedDataSection.noticeData.refNotice.map {
		val y = Integer.parseInt(it.replace(Regex("/.*"), ""))
		val n = Integer.parseInt(it.replace(Regex(".*-"), ""))
		"$n-$y"
	}.toList()

	/**
	 * Extracts the TED notice ID which is in form N-YYYY, then swaps number and year parts. Pads the number with zeros to fixed 6 character length. This way we get an orderable notice ID.
	 *
	 * @return Swapped notice ID in format YYYY-NNNNNN.
	 */
	fun swappedNoticeId() = tedExport!!.docId
			.split('-')
			.reversed()
			.mapIndexed { i, s ->
				if (0 == i) s
				else String.format("%6s", s).replace(' ', '0')
			}
			.joinToString("-")

	/**
	 * Tender ID should be the first notice ID in the tender. Unfortunately TED-XML contains references for only
	 * a direct parent notice, which is not always the first notice of a tender. If there's a reference, we have to
	 * look up its root. Otherwise, if there's no referenced notice, the current notice itself is the root, so the
	 * current notice ID will be the tender ID.
	 *
	 * This method is called by `parse` methods and it fills `tenderId` field.
	 *
	 * @return Current notice ID if this is the root; otherwise returns `null`.
	 */
	private fun tenderId(): String? = if (refNoticeIds().isEmpty()) swappedNoticeId() else null

	fun title(languageValue: LanguageValue) = tedExport!!.translationSection.mlTitles
			.filter { it.lg == languageValue }
			.map { "${it.tiCy} - ${it.tiTown}: ${it.tiText}" }
			.first()

	fun totalEstimatedValue() = values().firstOrNull { it.type == ValueType.ESTIMATED_TOTAL }

	fun totalFinalValue() = values().firstOrNull { it.type == ValueType.PROCUREMENT_TOTAL }

	fun url(body: ContactContractingBody?) = listOfNotNull(
			body?.urlBuyer,
			body?.urlGeneral,
			body?.url
	).map {
		try {
			val s = it.replace(Regex("^[^?]+%20http"), "http")
			// www.domain.tld%20http://www.domain.tld -> http://www.domain.tld
			URI(s)
		} catch (e: Exception) {
			null
		}
	}.firstOrNull()

	fun values() = mutableListOf<ValueOrValueRange>().apply {
		addAll(tedExport!!.codedDataSection.noticeData.values?.values ?: listOf())
		addAll(tedExport!!.codedDataSection.codifData.values?.values ?: listOf())
	}

	fun winners(awardContract: AwardContract) = listOfNotNull(
			awardContract.awardedContract?.contractors?.mapNotNull { it.addressContractingBody },
			awardContract.awardedContract?.contractorsAlt?.contractor?.mapNotNull { it.addressContractingBody }
	).flatten()
}