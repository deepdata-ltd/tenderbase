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

package hu.deepdata.tenderbase.tedxml.importer.util

import mu.*
import org.jsoup.*
import java.io.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
class EurConverter {

	companion object : KLogging() {
		val FILENAME = "eur-rates.properties"
		val URL = "http://www.xe.com/currencytables/?from=EUR"
	}

	private val rates = mutableMapOf<String, Double>()

	init {
		fetchRates()
	}

	fun convert(a: Double, c: String): Double? {
		return if (rates.containsKey(c)) a * rates[c]!! else null
	}

	private fun fetchRates() {
		fetchRatesFromFile()
		if (rates.isEmpty()) fetchRatesFromWeb()
		if (rates.isEmpty()) throw IllegalStateException("Could not fetch EUR currency rates")
	}

	private fun fetchRatesFromFile() {
		logger.info("Fetching EUR currency rates from file")
		try {
			val file = File(FILENAME)
			if (file.exists()) {
				file.inputStream().use {
					val p = Properties()
					p.load(it)
					p.forEach { c, r -> rates.put(c.toString(), r.toString().toDouble()) }
				}
			}
		} catch (e: Exception) {
			logger.trace("Could not load EUR currency rates from file", e)
		}
	}

	private fun fetchRatesFromWeb() {
		logger.info("Fetching EUR currency rates from web")
		Jsoup.connect(URL).get()?.select("#historicalRateTbl tbody tr")?.forEach { tr ->
			val tds = tr.select("td")
			if (tds.size >= 4) rates.put(tds[0].text(), tds[3].text().toDouble())
		}
		saveRatesToFile()
	}

	private fun saveRatesToFile() {
		val p = Properties()
		rates.forEach { c, r -> p.setProperty(c, r.toString()) }
		File(FILENAME).outputStream().use { p.store(it, null) }
	}
}