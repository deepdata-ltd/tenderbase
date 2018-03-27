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
import org.easybatch.core.processor.*
import org.easybatch.core.record.*
import java.util.concurrent.*

/**
 * @author Zsolt Jurányi
 */
class TarProcessor(private val context: TxiBatchContext) :
		RecordProcessor<Record<TarRecord>, Record<TarRecord>> {

	companion object : KLogging()

	private val xmlQueue = LinkedBlockingQueue<Record<*>>()

	override fun processRecord(record: Record<TarRecord>): Record<TarRecord> {
		with(context) {
			val tarRecord = record.payload
			logger.info("Processing TAR: ${tarRecord.tarMeta.name}")
			val report = executeMultiple(
					buildXmlCollectorJob(tarRecord, xmlQueue),
					buildXmlProcessorJob(tarRecord, xmlQueue)
			)
			with(tarRecord.tarMeta) {
				errors = report.metrics.errorCount
				if (errors > 0) logger.warn("TAR had $errors errors: $name")
			}
		}
		return record
	}
}