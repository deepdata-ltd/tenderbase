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

package hu.deepdata.tenderbase.tedxml.importer.conv

import com.fasterxml.jackson.databind.*
import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.tedxml.importer.util.*
import hu.deepdata.tenderbase.webapp.model.*

/**
 * @author Zsolt Jurányi
 */
class WReleaseConverter : IConverter<ReleaseSchema, WRelease> {

	companion object {
		fun crop(s: String?, len: Int) = when {
			null == s -> null
			s.length > len -> s.substring(0, len - 3) + "..."
			else -> s
		}
	}

	var eurConverter = EurConverter()

	override fun convert(input: ReleaseSchema) = WRelease().apply {
		id = input.id

		buyerCountry = input.parties.firstOrNull { it.roles.contains("buyer") }?.address?.countryName
		buyerName = crop(input.buyer?.name, 255)
		eurValue = toEur(input.tender?.value)

		var awardsEurValue = 0L
		input.awards?.forEach { awardsEurValue += toEur(it.value) ?: 0 }
		if (0 < awardsEurValue) eurValue = awardsEurValue

		ocid = input.ocid
		publishedAt = input.date
		tags = input.tag?.joinToString(",")
		title = crop(input.tender?.title, 255)

		json = generateJson(input)
	}

	fun generateJson(ocds: ReleaseSchema) = ObjectMapper().writeValueAsString(ocds)

	fun toEur(amount: Amount?): Long? {
		if (null == amount?.amount || null == amount?.currency) return null
		return eurConverter.convert(amount.amount, amount.currency.value())?.toLong()
	}
}