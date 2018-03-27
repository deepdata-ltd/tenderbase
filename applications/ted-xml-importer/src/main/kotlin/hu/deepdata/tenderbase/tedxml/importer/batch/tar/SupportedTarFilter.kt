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
import mu.*
import org.easybatch.core.record.*
import java.io.*

/**
 * @author Zsolt Jurányi
 */
class SupportedTarFilter : FileRecordFilter(SupportedTarFileFilter()) {

	companion object : KLogging()

	override fun processRecord(record: Record<File>): Record<File>? {
		logger.trace("Checking if TAR is supported: ${record.payload.name}")
		val r = super.processRecord(record)
		if (null == r) logger.debug("Unsupported TAR: ${record.payload.name}")
		return r
	}
}