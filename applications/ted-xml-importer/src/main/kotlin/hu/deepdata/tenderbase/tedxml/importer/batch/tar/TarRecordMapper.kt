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

import hu.deepdata.tenderbase.tedxml.importer.model.*
import mu.*
import org.easybatch.core.mapper.*
import org.easybatch.core.record.*
import org.springframework.util.*
import java.io.*
import java.util.*

/**
 * Converts a `Record<File>` to a `Record<TarRecord>` which will
 * contain the `File` object and empty metadata objects.
 *
 * @author Zsolt Jurányi
 */
class TarRecordMapper : RecordMapper<Record<File>, Record<TarRecord>> {

	companion object : KLogging()

	override fun processRecord(record: Record<File>): Record<TarRecord> {
		logger.trace("Generating TAR metadata: ${record.payload.name}")
		return GenericRecord<TarRecord>(record.header, convertFile(record.payload))
	}

	private fun convertFile(file: File): TarRecord =
			TarRecord(file, generateMeta(file))

	private fun generateMeta(file: File): FileMeta {
		val meta = FileMeta(generateId(file), file.name, Date())
		logger.trace("Generated TAR metadata: $meta")
		return meta
	}

	private fun generateId(file: File) =
			file.name + "$" + generateChecksum(file)

	private fun generateChecksum(file: File): String {
		file.inputStream().use {
			return DigestUtils.md5DigestAsHex(it)
		}
	}

}