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
import org.easybatch.core.mapper.*
import org.easybatch.core.record.*

/**
 * @author Zsolt Jurányi
 */
class XmlFinalizer : RecordMapper<Record<XmlRecord>, Record<List<Any>>> {

	override fun processRecord(record: Record<XmlRecord>): GenericRecord<List<Any>> {
		return GenericRecord(record.header, listOfNotNull(
				record.payload.xmlMeta,
				record.payload.wRelease,
				*(record.payload.wOrganizations ?: listOf<Any>()).toTypedArray() as Array<*>,
				*(record.payload.wParticipations ?: listOf<Any>()).toTypedArray() as Array<*>
		))
	}


}