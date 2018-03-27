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
import mu.*
import org.easybatch.core.filter.*
import org.easybatch.core.record.*
import java.util.regex.*

/**
 * @author Zsolt Jurányi
 */
class SupportedXmlFilter : RecordFilter<Record<XmlRecord>> {

	companion object : KLogging()

	override fun processRecord(record: Record<XmlRecord>): Record<XmlRecord>? {
		with(record.payload) {
			val filename = xmlMeta.name!!
			logger.trace("Checking if XML is supported: $filename")
			val matcher = Pattern.compile("VERSION=\"([^\"]+)\"").matcher(xmlContent)
			val schema = if (matcher.find()) matcher.group(1) else ""
			return if (accept(filename, schema)) {
				record
			} else {
				logger.debug("Unsupported XML (schema: $schema): $filename")
				null
			}
		}
	}

	private fun accept(filename: String, schema: String): Boolean {
		return filename.matches(Regex(".*/?\\d+_\\d{4}\\.xml"))
				&& schema.matches(Regex("^R2\\.0\\.9\\..*"))
	}
}