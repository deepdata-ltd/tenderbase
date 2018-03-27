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

import org.easybatch.core.reader.*
import org.easybatch.core.record.*
import java.io.*
import java.util.*
import kotlin.NoSuchElementException

/**
 * @author Zsolt Jurányi
 */
class CustomFileRecordReader(private val directory: File) : RecordReader {

	private val iterator = DirectoryContentIterator(directory)

	override fun close() {}

	override fun open() {}

	override fun readRecord(): Record<File>? {
		return if (iterator.hasNext()) recordFrom(iterator.next()) else null
	}

	private fun recordFrom(file: File): Record<File> {
		val h = Header(System.currentTimeMillis(), directory.absolutePath, Date())
		return GenericRecord(h, file)
	}

	class DirectoryContentIterator(directory: File) : Iterator<File> {

		private val stack = Stack<Iterator<File>>().apply {
			listFiles(directory)?.also { push(it) }
		}
		private var next: File? = null

		override fun hasNext(): Boolean {
			if (null == next) next = findNext()
			return null != next
		}

		override fun next(): File {
			if (hasNext()) {
				val f = next!!
				next = null // mark it as taken so hasNext will jump
				return f
			} else throw NoSuchElementException()
		}

		private fun findNext(): File? {
			while (stack.isNotEmpty()) {
				if (stack.peek().hasNext()) {
					val f = stack.peek().next()
					if (f.isDirectory) stack.push(listFiles(f)) else return f
				} else stack.pop()
			}
			return null
		}

		private fun listFiles(directory: File) = if (directory.isDirectory) {
			directory.listFiles().toList().sortedWith(compareBy { it.name.toUpperCase() }).iterator()
		} else null
	}

}