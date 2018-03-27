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
data class AwardedContract(
		@field:Element(name = "AWARDED_TO_GROUP", required = false)
		var awardedToGroup: Unit? = null,

		@field:ElementList(entry = "CONTRACTOR", inline = true, required = false)
		var contractors: List<Contractor>? = mutableListOf(),

		@field:Element(name = "CONTRACTORS", required = false)
		var contractorsAlt: Contractors? = null,

		@field:Element(name = "DATE_CONCLUSION_CONTRACT", required = false)
		@field:Convert(DateConverter::class)
		var dateConclusionContract: Date? = null,

		@field:Element(name = "INFO_ADD_SUBCONTRACTING", required = false)
		@field:Convert(PConverter::class)
		var infoAddSubcontracting: String? = null,

		@field: Element(name = "INFO_ADD_VALUE", required = false)
		@field:Convert(PConverter::class)
		var infoAddValue: String? = null,

		@field:Element(name = "LIKELY_SUBCONTRACTED", required = false)
		var likelySubcontracted: Unit? = null,

		@field:Element(name = "NB_TENDERS_RECEIVED", required = false)
		@field:Convert(IntConverter::class)
		var nbTendersReceived: Int? = null,

		@field:Element(name = "NB_TENDERS_RECEIVED_EMEANS", required = false)
		@field:Convert(IntConverter::class)
		var nbTendersReceivedEmeans: Int? = null,

		@field:Element(name = "NB_TENDERS_RECEIVED_NON_EU", required = false)
		@field:Convert(IntConverter::class)
		var nbTendersReceivedNonEu: Int? = null,

		@field:Element(name = "NB_TENDERS_RECEIVED_OTHER_EU", required = false)
		@field:Convert(IntConverter::class)
		var nbTendersReceivedOtherEu: Int? = null,

		@field:Element(name = "NB_TENDERS_RECEIVED_SME", required = false)
		@field:Convert(IntConverter::class)
		var nbTendersReceivedSme: Int? = null,

		@field:Element(name = "NO_AWARDED_TO_GROUP", required = false)
		var noAwardedToGroup: Unit? = null,

		@field:Element(name = "PCT_SUBCONTRACTING", required = false)
		@field:Convert(IntConverter::class)
		var pctSubcontracting: Int? = null,

		@field:Element(name = "VAL_ESTIMATED_TOTAL", required = false)
		var valEstimatedTotal: Value? = null,

		@field:Element(name = "VAL_PRICE_PAYMENT", required = false)
		var valPricePayment: Value? = null,

		@field:Element(name = "VAL_RANGE_TOTAL", required = false)
		var valRangeTotal: ValueRange? = null,

		@field:Element(name = "VAL_REVENUE", required = false)
		var valRevenue: Value? = null,

		@field:Element(name = "VAL_SUBCONTRACTING", required = false)
		var valSubcontracting: Value? = null,

		@field:Element(name = "VAL_TOTAL", required = false)
		var valTotal: Value? = null,

		@field:Element(name = "VALUE", required = false)
		var valueAlt: AwardValue? = null
)