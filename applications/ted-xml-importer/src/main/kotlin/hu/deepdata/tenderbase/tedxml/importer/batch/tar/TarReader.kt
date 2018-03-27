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
import org.apache.commons.compress.archivers.tar.*
import org.apache.commons.compress.compressors.gzip.*
import org.easybatch.core.reader.*
import org.easybatch.core.record.*
import org.springframework.util.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
class TarReader(private val tarRecord: TarRecord) : RecordReader {

	companion object : KLogging()

	var recordNo = 0L
	var tar: TarArchiveInputStream? = null

	override fun open() {
		tar = TarArchiveInputStream(GzipCompressorInputStream(tarRecord.file.inputStream()))
	}

	override fun readRecord(): Record<*>? {
		// get next TAR entry, exit if reached end
		val entry = tar?.nextTarEntry ?: return null
		logger.trace("Reading TAR entry: ${entry.name}")

		// get XML content
		val nonClosingTar = StreamUtils.nonClosing(tar)
		val content = StreamUtils.copyToString(nonClosingTar, Charsets.UTF_8)

		// build record
		val id = generateId(entry, content)
		val xmlMeta = FileMeta(id, entry.name, Date(), tarId = tarRecord.tarMeta.id)
		val record = XmlRecord(tarRecord.tarMeta, xmlMeta, content)
		val header = Header(++recordNo, tarRecord.file.name, Date())
		return GenericRecord<XmlRecord>(header, record)
	}

	override fun close() {
		tar?.close()
	}

	private fun generateId(entry: TarArchiveEntry, content: String): String =
			entry.name + "$" + generateChecksum(content)

	private fun generateChecksum(s: String): String =
			DigestUtils.md5DigestAsHex(s.toByteArray(Charsets.UTF_8))
}