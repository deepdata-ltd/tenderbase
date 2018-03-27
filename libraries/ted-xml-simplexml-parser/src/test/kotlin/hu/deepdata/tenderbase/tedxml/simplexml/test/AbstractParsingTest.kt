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

package hu.deepdata.tenderbase.tedxml.simplexml.test

import hu.deepdata.tenderbase.tedxml.simplexml.model.*
import hu.deepdata.tenderbase.tedxml.simplexml.parser.*
import org.junit.*
import org.junit.Assert.*
import org.junit.Assume.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
abstract class AbstractParsingTest {

	companion object {
		val tedExports = mutableMapOf<String, TedExport>()
	}

	protected var tedExport: TedExport

	init {
		val k = config().resource
		if (null == tedExports[k]) {
			println("Parsing resource: $k")
			var t = -System.currentTimeMillis()
			tedExports[k] = TedExportParser().parse(k).get()!!
			t += System.currentTimeMillis()
			println("Resource $k parsed in $t ms")
		}
		tedExport = tedExports[k]!!
	}

	abstract protected fun config(): Config

	protected data class Config(
			var resource: String,
			// content
			var datePub: Date? = null,
			var directive: String? = null,
			var docId: String? = null,
			var dtDateForSubmission: Date? = null,
			var duration: Duration? = null,
			var isoCountry: String? = null,
			var lgOrig: List<LanguageValue>? = null,
			var maMainActivities: List<TextAndCode>? = null,
			var originalCpv: List<TextAndCode>? = null,
			var tdDocumentType: TextAndCode? = null,
			var values: List<ValueOrValueRange>? = null,
			var version: String? = null
	)

	protected fun test(expected: Any?, actual: Any?) {
		assumeNotNull(expected)
		assertEquals(expected, actual)
	}

	@Test
	fun datePub() {
		test(config().datePub, tedExport.codedDataSection.refOjs.datePub)
	}

	@Test
	fun directive() {
		test(config().directive, tedExport.codedDataSection.codifData.directive)
	}

	@Test
	fun docId() {
		test(config().docId, tedExport.docId)
	}

	@Test
	fun dtDateForSubmission() {
		test(config().dtDateForSubmission, tedExport.codedDataSection.codifData.dtDateForSubmission)
	}

	@Test
	fun duration() {
		val f = tedExport.formSection.forms.firstOrNull { it.category == Category.ORIGINAL }
		val d = f?.procedure?.durationTenderValid
		test(config().duration, d)
	}

	@Test
	fun isoCountry() {
		test(config().isoCountry, tedExport.codedDataSection.noticeData.isoCountry)
	}

	@Test
	fun maMainActivities() {
		test(config().maMainActivities, tedExport.codedDataSection.codifData.maMainActivities)
	}

	@Test
	fun lgOrig() {
		test(config().lgOrig?.map(LanguageValue::name), tedExport.codedDataSection.noticeData.lgOrig)
	}

	@Test
	fun originalCpv() {
		test(config().originalCpv, tedExport.codedDataSection.noticeData.originalCpv)
	}

	@Test
	fun tdDocumentType() {
		test(config().tdDocumentType, tedExport.codedDataSection.codifData.tdDocumentType)
	}

	@Test
	fun values() {
		test(config().values, tedExport.codedDataSection.noticeData.values?.values)
	}

	@Test
	fun version() {
		test(config().version, tedExport.version)
	}
}
