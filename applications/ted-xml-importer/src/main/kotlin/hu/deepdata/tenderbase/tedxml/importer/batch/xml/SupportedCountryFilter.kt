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

package hu.deepdata.tenderbase.tedxml.importer.batch.xml

import hu.deepdata.tenderbase.tedxml.importer.model.*
import hu.deepdata.tenderbase.tedxml.simplexml.parser.*
import mu.*
import org.easybatch.core.filter.*
import org.easybatch.core.record.*

/**
 * @author Zsolt Jurányi
 */
class SupportedCountryFilter(countryListStr: String?) : RecordFilter<Record<XmlRecord>> {

	companion object : KLogging()

	var countryList = countryListStr?.toUpperCase()?.split(",") ?: listOf()

	override fun processRecord(record: Record<XmlRecord>): Record<XmlRecord>? {
		logger.trace("Checking if country is supported: ${record.payload.xmlMeta.name}")
		record.payload.tedExport?.also {
			val parser = TedExportParser(it)
			val country = parser.buyer()?.country?.toUpperCase() ?: ""
			if (!countryList.isEmpty() && countryList.contains(country)) return record
			logger.debug("Unsupported country ($country): ${it.docId}")
		}
		return null
	}
}