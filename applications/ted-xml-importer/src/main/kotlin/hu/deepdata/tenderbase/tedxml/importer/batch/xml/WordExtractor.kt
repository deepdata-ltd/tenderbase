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
class WordExtractor : RecordMapper<Record<XmlRecord>, Record<XmlRecord>> {

	override fun processRecord(record: Record<XmlRecord>) = record.apply {
		val json = payload.wRelease?.json ?: ""
		var words = extractWordsFromJSON(json)
		words = removePrefixWords(words)
		payload.wRelease?.words = words.joinToString(",")
	}

	private fun extractWordsFromJSON(s: String) = s
			// OCDS ids
			.replace(Regex("ocds-[^\"]+\""), " ")
			// JSON field names
			.replace(Regex("\"[^ '\",:{}]+\":"), " ")
			// JSON symbols
			.replace(Regex("[,.:{}'\"]|\\[|\\]"), " ")
			// irrelevant text parts
			.replace(Regex("\\\\n"), " ")
			.replace(Regex("(\\d|[.,:;!?'/&+*=%—(){}@]|-|\\\\|\\[|]|\")+"), " ")
			// whitespace
			.replace(Regex(" +"), " ")
			.trim()
			// generate words
			.toLowerCase()
			.split(" ")
			.sorted()
			.distinct()
			.filter { it.length >= 3 }

	private fun removePrefixWords(words: List<String>): List<String> {
		return words.filter { w ->
			var r = true
			for (i in 0 until words.size) {
				if (words[i] != w && words[i].startsWith(w)) {
					r = false
					break
				}
				if (w < words[i]) break
			}
			r
		}
	}
}