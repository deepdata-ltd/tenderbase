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

package hu.deepdata.tenderbase.tedxml.importer.batch

import hu.deepdata.tenderbase.tedxml.importer.model.*
import mu.*
import org.easybatch.core.processor.*
import org.easybatch.core.record.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
class FileMetaSigner(private val currentVersionStr: String) : RecordProcessor<Record<*>, Record<*>?> {

	companion object : KLogging()

	override fun processRecord(record: Record<*>): Record<*>? {
		if (record.payload is FileMeta) {
			return GenericRecord<FileMeta>(record.header, signFileMeta(record.payload as FileMeta))
		} else if (record.payload is Collection<*>) {
			val c = record.payload as Collection<*>
			val nc = mutableListOf<Any>()
			c.forEach { e ->
				if (e is FileMeta) nc.add(signFileMeta(e)) else nc.add(e!!)
			}
			return GenericRecord<Any>(record.header, nc)
		}
		return null
	}

	fun signFileMeta(fileMeta: FileMeta) = fileMeta.apply {
		logger.trace("Signing file metadata: ${fileMeta.name}")
		processedAt = if (0L == errors) Date() else null
		processVersion = currentVersionStr
	}
}