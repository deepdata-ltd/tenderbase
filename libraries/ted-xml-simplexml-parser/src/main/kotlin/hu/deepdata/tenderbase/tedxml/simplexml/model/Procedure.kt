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
data class Procedure(
		@field:Element(name = "ACCELERATED_PROC", required = false)
		@field:Convert(PConverter::class)
		var acceleratedProc: String? = null,

		@field:Element(name = "CONTRACT_COVERED_GPA", required = false)
		var contractCoveredGpa: Unit? = null,

		@field:Element(name = "CRITERIA_EVALUATION", required = false)
		@field:Convert(PConverter::class)
		var criteriaEvaluation: String? = null,

		@field:Element(name = "DATE_AWARD_SCHEDULED", required = false)
		@field:Convert(DateConverter::class)
		var dateAwardScheduled: Date? = null,

		@field:Element(name = "DATE_DISPATCH_INVITATIONS", required = false)
		@field:Convert(DateConverter::class)
		var dateDispatchInvitations: Date? = null,

		@field:Element(name = "DATE_RECEIPT_TENDERS", required = false)
		@field:Convert(DateConverter::class)
		var dateReceiptTenders: Date? = null,

		@field:Element(name = "DATE_TENDER_VALID", required = false)
		@field:Convert(DateConverter::class)
		var dateTenderValid: Date? = null,

		@field:Element(name = "DECISION_BINDING_CONTRACTING", required = false)
		var decisionBindingContracting: Unit? = null,

		@field:Element(name = "DETAILS_PAYMENT", required = false)
		@field:Convert(PConverter::class)
		var detailsPayment: String? = null,

		@field:Element(name = "DIRECTIVE_2009_81_EC", required = false)
		var directive200981EC: Pt? = null,

		@field:Element(name = "DIRECTIVE_2014_23_EU", required = false)
		var directive201423EU: Pt? = null,

		@field:Element(name = "DIRECTIVE_2014_24_EU", required = false)
		var directive201424EU: Pt? = null,

		@field:Element(name = "DIRECTIVE_2014_25_EU", required = false)
		var directive201425EU: Pt? = null,

		@field:Element(name = "DPS", required = false)
		var dps: Unit? = null,

		@field:Element(name = "DPS_ADDITIONAL_PURCHASERS", required = false)
		var dpsAdditionalPurchasers: Unit? = null,

		@field:Element(name = "DURATION_TENDER_VALID", required = false)
		@field:Convert(DurationConverter::class)
		var durationTenderValid: Duration? = null,

		@field:Element(name = "EAUCTION_USED", required = false)
		var eauctionUsed: Unit? = null,

		@field:Element(name = "FOLLOW_UP_CONTRACTS", required = false)
		var followUpContracts: Unit? = null,

		@field:Element(name = "FRAMEWORK", required = false)
		var framework: FrameworkInfo? = null,

		@field:Element(name = "INFO_ADD_EAUCTION", required = false)
		@field:Convert(PConverter::class)
		var infoAddEauction: String? = null,

		@field:ElementList(name = "LANGUAGES", entry = "LANGUAGE", required = false)
		var languages: List<Language> = mutableListOf(),

		@field:Element(name = "MAIN_FEATURES_AWARD", required = false)
		@field:Convert(PConverter::class)
		var mainFeaturesAward: String? = null,

		@field:ElementList(entry = "MEMBER_NAME", inline = true, required = false)
		var memberName: List<String>? = null,

		@field:Element(name = "NB_MAX_PARTICIPANTS", required = false)
		@field:Convert(IntConverter::class)
		var nbMaxParticipants: Int? = null,

		@field:Element(name = "NB_MIN_PARTICIPANTS", required = false)
		@field:Convert(IntConverter::class)
		var nbMinParticipants: Int? = null,

		@field:Element(name = "NO_CONTRACT_COVERED_GPA", required = false)
		var noContractCoveredGpa: Unit? = null,

		@field:Element(name = "NO_DECISION_BINDING_CONTRACTING", required = false)
		var noDecisionBindingContracting: Unit? = null,

		@field:Element(name = "NO_FOLLOW_UP_CONTRACTS", required = false)
		var noFollowUpContracts: Unit? = null,

		@field:Element(name = "NO_PRIZE_AWARDED", required = false)
		var noPrizeAwarded: Unit? = null,

		@field:Element(name = "NOTICE_NUMBER_OJ", required = false)
		var noticeNumberOj: String? = null,

		@field:Element(name = "NUMBER_VALUE_PRIZE", required = false)
		@field:Convert(PConverter::class)
		var numberValuePrize: String? = null,

		@field:Element(name = "OPENING_CONDITION", required = false)
		var openingCondition: CondForOpeningTenders? = null,

		@field:ElementList(entry = "PARTICIPANT_NAME", inline = true, required = false)
		var participantName: List<String>? = null,

		@field:Element(name = "PRIZE_AWARDED", required = false)
		var prizeAwarded: Unit? = null,

		@field:Element(name = "PT_AWARD_CONTRACT_WITH_PRIOR_PUBLICATION", required = false)
		var ptAwardContractWithPriorPublication: Unit? = null,

		@field:Element(name = "PT_AWARD_CONTRACT_WITHOUT_CALL", required = false)
		var ptAwardContractWithoutCall: Annex? = null,

		@field:Element(name = "PT_AWARD_CONTRACT_WITHOUT_PUBLICATION", required = false)
		var ptAwardContractWithoutPublication: Annex? = null,

		@field:Element(name = "PT_COMPETITIVE_DIALOGUE", required = false)
		var ptCompetitiveDialogue: Unit? = null,

		@field:Element(name = "PT_COMPETITIVE_NEGOTIATION", required = false)
		var ptCompetitiveNegotiation: Unit? = null,

		@field:Element(name = "PT_INNOVATION_PARTNERSHIP", required = false)
		var ptInnovationPartnership: Unit? = null,

		@field:Element(name = "PT_INVOLVING_NEGOTIATION", required = false)
		var ptInvolvingNegotiation: Unit? = null,

		@field:Element(name = "PT_NEGOTIATED_WITH_PRIOR_CALL", required = false)
		var ptNegotiatedWithPriorCall: Unit? = null,

		@field:Element(name = "PT_OPEN", required = false)
		var ptOpen: Unit? = null,

		@field:Element(name = "PT_RESTRICTED", required = false)
		var ptRestricted: Unit? = null,

		@field:Element(name = "REDUCTION_RECOURSE", required = false)
		var reductionRecourse: Unit? = null,

		@field:Element(name = "RIGHT_CONTRACT_INITIAL_TENDERS", required = false)
		var rightContractInitialTenders: Unit? = null,

		@field:Element(name = "TERMINATION_DPS", required = false)
		var terminationDps: Unit? = null,

		@field:Element(name = "TERMINATION_PIN", required = false)
		var terminationPin: Unit? = null,

		@field:Element(name = "TIME_RECEIPT_TENDERS", required = false)
		var timeReceiptTenders: String? = null,

		@field:Element(name = "URL_NATIONAL_PROCEDURE", required = false)
		var urlNationalProcedure: String? = null
)