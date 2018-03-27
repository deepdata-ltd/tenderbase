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

package hu.deepdata.tenderbase.tedxml.importer.model

import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.tedxml.simplexml.model.*
import hu.deepdata.tenderbase.webapp.model.*

/**
 * @author Zsolt Jurányi
 */
data class XmlRecord(
		var tarMeta: FileMeta,
		var xmlMeta: FileMeta,
		var xmlContent: String,
		var tedExport: TedExport? = null,
		var ocdsRelease: ReleaseSchema? = null,
		var wRelease: WRelease? = null,
		var wOrganizations: Set<WOrganization>? = null,
		var wParticipations: Set<WParticipation>? = null
) {
	override fun toString(): String = "XmlRecord(tarMeta=$tarMeta, xmlMeta=$xmlMeta, xmlContent='${xmlContent.length}')"
}