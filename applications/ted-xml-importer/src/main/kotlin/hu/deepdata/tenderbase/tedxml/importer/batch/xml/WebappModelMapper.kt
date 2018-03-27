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

import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.tedxml.importer.conv.*
import hu.deepdata.tenderbase.tedxml.importer.model.*
import hu.deepdata.tenderbase.webapp.model.*
import mu.*
import org.easybatch.core.processor.*
import org.easybatch.core.record.*

/**
 * @author Zsolt Jurányi
 */
class WebappModelMapper(
		private val wReleaseConverter: IConverter<ReleaseSchema, WRelease>,
		private val wOrganizationsConverter: IConverter<ReleaseSchema, Set<WOrganization>>,
		private val wParticipationsConverter: IConverter<Pair<ReleaseSchema, WRelease>, Set<WParticipation>>
) : RecordProcessor<Record<XmlRecord>, Record<XmlRecord>> {

	companion object : KLogging()

	override fun processRecord(record: Record<XmlRecord>) = record.apply {
		logger.trace("Generating webapp model: ${payload.xmlMeta.name}")
		payload.ocdsRelease?.also {
			payload.wRelease = wReleaseConverter.convert(it)
			payload.wOrganizations = wOrganizationsConverter.convert(it)
			payload.wParticipations = wParticipationsConverter.convert(Pair(it, payload.wRelease!!))
		}
	}

}