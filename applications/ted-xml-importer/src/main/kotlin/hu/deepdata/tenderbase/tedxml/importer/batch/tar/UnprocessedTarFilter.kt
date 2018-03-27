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

package hu.deepdata.tenderbase.tedxml.importer.batch.tar

import hu.deepdata.tenderbase.tedxml.importer.batch.*
import hu.deepdata.tenderbase.tedxml.importer.model.*
import mu.*
import org.easybatch.core.filter.*
import org.easybatch.core.record.*

/**
 * @author Zsolt Jurányi
 */
class UnprocessedTarFilter(val unprocessedMetaFilter: UnprocessedMetaFilter) : RecordFilter<Record<TarRecord>> {

	companion object : KLogging()

	override fun processRecord(record: Record<TarRecord>): Record<TarRecord>? {
		val tar = record.payload.tarMeta
		logger.trace("Checking if TAR is processed: ${tar.name}")
		val xmls = record.payload.xmlMetas
		var processed = unprocessedMetaFilter.isProcessed(tar)
				&& xmls.all(unprocessedMetaFilter::isProcessed)
		return if (processed) {
			logger.debug("Already processed TAR: ${tar.name}")
			null
		} else record
	}

}