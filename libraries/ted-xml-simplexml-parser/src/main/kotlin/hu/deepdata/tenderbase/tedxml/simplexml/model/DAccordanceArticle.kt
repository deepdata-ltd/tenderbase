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
data class DAccordanceArticle(
		@field:Element(name = "D_ADD_DELIVERIES_ORDERED", required = false)
		var dAddDeliveriesOrdered: Unit? = null,

		@field:Element(name = "D_ALL_TENDERS", required = false)
		var dAllTenders: Unit? = null,

		@field:Element(name = "D_ARTISTIC", required = false)
		var dArtistic: Unit? = null,

		@field:Element(name = "D_BARGAIN_PURCHASE", required = false)
		var dBargainPurchase: Unit? = null,

		@field:Element(name = "D_COMMODITY_MARKET", required = false)
		var dCommodityMarket: TypeContract? = null,

		@field:Element(name = "D_CONTRACT_AWARDED_DESIGN_CONTEST", required = false)
		var dContractAwardedDesignContest: TypeContract? = null,

		@field:Element(name = "D_EXCLUSIVE_RIGHT", required = false)
		var dExclusiveRight: Unit? = null,

		@field:Element(name = "D_EXTREME_URGENCY", required = false)
		var dExtremeUrgency: Unit? = null,

		@field:Element(name = "D_FROM_LIQUIDATOR_CREDITOR", required = false)
		var dFromLiquidatorCreditor: TypeContract? = null,

		@field:Element(name = "D_FROM_WINDING_PROVIDER", required = false)
		var dFromWindingProvider: TypeContract? = null,

		@field:Element(name = "D_MARITIME_SERVICES", required = false)
		var dMaritimeServices: TypeContract? = null,

		@field:Element(name = "D_NO_TENDERS_REQUESTS", required = false)
		var dNoTendersRequests: Unit? = null,

		@field:Element(name = "D_OTHER_SERVICES", required = false)
		var dOtherServices: TypeContract? = null,

		@field:Element(name = "D_PERIODS_INCOMPATIBLE", required = false)
		var dPeriodsIncompatible: Unit? = null,

		@field:Element(name = "D_PROC_COMPETITIVE_DIALOGUE", required = false)
		var dProcCompetitiveDialogue: Unit? = null,

		@field:Element(name = "D_PROC_NEGOTIATED_PRIOR_CALL_COMPETITION", required = false)
		var dProcNegotiatedPriorCallCompetition: Unit? = null,

		@field:Element(name = "D_PROC_OPEN", required = false)
		var dProcOpen: Unit? = null,

		@field:Element(name = "D_PROC_RESTRICTED", required = false)
		var dProcRestricted: Unit? = null,

		@field:Element(name = "D_PROTECT_RIGHTS", required = false)
		var dProtectRights: Unit? = null,

		@field:Element(name = "D_PURE_RESEARCH", required = false)
		var dPureResearch: Unit? = null,

		@field:Element(name = "D_REPETITION_EXISTING", required = false)
		var dRepetitionExisting: TypeContract? = null,

		@field:Element(name = "D_TECHNICAL", required = false)
		var dTechnical: Unit? = null,

		@field:Element(name = "D_MANUF_FOR_RESEARCH", required = false)
		var dManufForResearch: TypeContract? = null
)