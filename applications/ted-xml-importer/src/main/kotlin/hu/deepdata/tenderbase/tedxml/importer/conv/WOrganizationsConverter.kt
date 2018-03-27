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
import hu.deepdata.tenderbase.webapp.model.*

/**
 * @author Zsolt Jurányi
 */
class WOrganizationsConverter : IConverter<ReleaseSchema, Set<WOrganization>> {

	private val mapper = ObjectMapper()

	override fun convert(input: ReleaseSchema) = input.parties.map {
		val json = mapper.writeValueAsString(it)
		WOrganization(it.id, json, WReleaseConverter.crop(it.name, 255))
	}.toSet()
}