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

package hu.deepdata.tenderbase.tedxml.simplexml.parser

import mu.*
import org.simpleframework.xml.convert.*
import org.simpleframework.xml.stream.*
import java.text.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
class DateConverter : Converter<Date?> {

	companion object : KLogging()

	override fun read(n: InputNode): Date? {
		var d = n.value.trim()
		try {
			if (d.matches(Regex("\\d{8}"))) {
				return SimpleDateFormat("yyyyMMdd").parse(d)
			} else if (d.matches(Regex("\\d{8} \\d{2}:\\d{2}"))) {
				return SimpleDateFormat("yyyyMMdd HH:mm").parse(d)
			} else if (d.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
				return SimpleDateFormat("yyyy-MM-dd").parse(d)
			}
			// TODO log unknown format!
		} catch (e: Exception) {
			logger.warn("failed to parse date: ${n.value}", e)
		}
		return null
	}

	override fun write(n: OutputNode, d: Date?) {
		throw UnsupportedOperationException()
	}
}