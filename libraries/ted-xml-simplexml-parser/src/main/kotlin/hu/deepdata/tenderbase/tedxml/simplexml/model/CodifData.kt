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

import hu.deepdata.tenderbase.tedxml.simplexml.parser.*
import org.simpleframework.xml.*
import org.simpleframework.xml.convert.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
data class CodifData(
		@field:Element(name = "AA_AUTHORITY_TYPE")
		var aaAuthorityType: TextAndCode? = null,

		@field:Element(name = "AC_AWARD_CRIT")
		var acAwardCrit: TextAndCode? = null,

		@field:Element(name = "DD_DATE_REQUEST_DOCUMENT", required = false)
		@field:Convert(DateConverter::class)
		var ddDateRequestDocument: Date? = null,

		@field:Attribute(name = "VALUE", required = false)
		@field:Path("DIRECTIVE")
		var directive: String? = null,

		@field:Element(name = "DS_DATE_DISPATCH")
		@field:Convert(DateConverter::class)
		var dsDateDispatch: Date? = null,

		@field:Element(name = "DT_DATE_FOR_SUBMISSION", required = false)
		@field:Convert(DateConverter::class)
		var dtDateForSubmission: Date? = null,

		@field:Element(name = "HEADING", required = false)
		var heading: String? = null,

		@field:Element(name = "INITIATOR")
		var initiator: String? = null,

		@field:ElementList(inline = true, entry = "MA_MAIN_ACTIVITIES", required = false)
		var maMainActivities: List<TextAndCode> = mutableListOf(),

		@field:Element(name = "NC_CONTRACT_NATURE")
		var ncContractNature: TextAndCode? = null,

		@field:Element(name = "PR_PROC")
		var prProc: TextAndCode? = null,

		@field:Element(name = "RP_REGULATION")
		var rpRegulation: TextAndCode? = null,

		@field:Element(name = "TD_DOCUMENT_TYPE")
		var tdDocumentType: TextAndCode? = null,

		@field:Element(name = "TY_TYPE_BID")
		var tyTypeBid: TextAndCode? = null,

		@field:Element(name = "VALUES", required = false)
		var values: Values? = null
)