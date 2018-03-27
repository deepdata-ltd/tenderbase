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

import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.webapp.model.*

/**
 * @author Zsolt Jurányi
 */
class WParticipationsConverter : IConverter<Pair<ReleaseSchema, WRelease>, Set<WParticipation>> {

	override fun convert(input: Pair<ReleaseSchema, WRelease>): Set<WParticipation> {
		val ocds = input.first
		val wRelease = input.second
		return ocds.parties.map { o ->
			o.roles.map { r ->
				WParticipation("${wRelease.id}:${o.id}:$r", r, o.id, wRelease.id)
			}
		}.flatten().toSet()
	}
}