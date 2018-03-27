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

package hu.deepdata.tenderbase.tedxml.simplexml.model

import org.simpleframework.xml.*

/**
 * @author Zsolt Jurányi
 */
data class NoticeData(

		// TODO URI_LIST - TED links to notice in every lang, can be generated, not so important

		@field:ElementList(inline = true, entry = "CA_CE_NUTS")
		var caCeNuts: List<TextAndCode> = mutableListOf(),

		@field:ElementList(inline = true, entry = "CURRENT_CPV", required = false)
		var currentCpv: List<String> = mutableListOf(),

		@field:ElementList(inline = true, entry = "CURRENT_NUTS", required = false)
		var currentNuts: List<String> = mutableListOf(),

		@field:Element(name = "IA_URL_ETENDERING", required = false)
		var iaUrlEtendering: String? = null,

		@field:Element(name = "IA_URL_GENERAL")
		var iaUrlGeneral: String? = null,

		@field:Attribute(name = "VALUE")
		@field:Path("ISO_COUNTRY")
		var isoCountry: String? = null,

		@field:ElementList(inline = true, entry = "LG_ORIG")
		var lgOrig: List<String> = mutableListOf(), // for some reason, LanguageValue type not working here

		@field:Element(name = "NO_DOC_OJS", required = false)
		var noDocOjs: String? = null,

		@field:ElementList(inline = true, entry = "ORIGINAL_CPV")
		var originalCpv: List<TextAndCode> = mutableListOf(),

		@field:ElementList(inline = true, entry = "ORIGINAL_NUTS", required = false)
		var originalNuts: List<TextAndCode> = mutableListOf(),

		@field:ElementList(name = "REF_NOTICE", entry = "NO_DOC_OJS", required = false)
		var refNotice: List<String> = mutableListOf(),

		@field:ElementList(inline = true, entry = "TENDERER_NUTS", required = false)
		var tendererNuts: List<TextAndCode> = mutableListOf(),

		@field:Element(name = "VALUES", required = false)
		var values: Values? = null
)