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

/**
 * @author Zsolt Jurányi
 */
data class Lefti(
		@field:Element(name = "CRITERIA_SELECTION", required = false)
		@field:Convert(PConverter::class)
		var criteriaSelection: String? = null,

		@field:Element(name = "DEPOSIT_GUARANTEE_REQUIRED", required = false)
		@field:Convert(PConverter::class)
		var depositGuaranteeRequired: String? = null,

		@field:Element(name = "ECONOMIC_CRITERIA_DOC", required = false)
		var economicCriteriaDoc: Unit? = null,

		@field:Element(name = "ECONOMIC_FINANCIAL_INFO", required = false)
		@field:Convert(PConverter::class)
		var economicFinancialInfo: String? = null,

		@field:Element(name = "ECONOMIC_FINANCIAL_MIN_LEVEL", required = false)
		@field:Convert(PConverter::class)
		var economicFinancialMinLevel: String? = null,

		@field:Element(name = "MAIN_FINANCING_CONDITION", required = false)
		@field:Convert(PConverter::class)
		var mainFinancingCondition: String? = null,

		@field:Element(name = "LEGAL_FORM", required = false)
		@field:Convert(PConverter::class)
		var legalForm: String? = null,

		@field:Element(name = "NO_PARTICULAR_PROFESSION", required = false)
		var noParticularProfession: Unit? = null,

		@field:Element(name = "PARTICULAR_PROFESSION", required = false)
		var particularProfession: TypeContract? = null,

		@field:Element(name = "PERFORMANCE_CONDITIONS", required = false)
		@field:Convert(PConverter::class)
		var performanceConditions: String? = null,

		@field:Element(name = "PERFORMANCE_STAFF_QUALIFICATION", required = false)
		var performanceStaffQualification: Unit? = null,

		@field:ElementList(entry = "QUALIFICATION", inline = true, required = false)
		var qualification: List<Qualification> = mutableListOf(),

		@field:Element(name = "REFERENCE_TO_LAW", required = false)
		@field:Convert(PConverter::class)
		var referenceToLaw: String? = null,

		@field:Element(name = "RESERVED_ORGANISATIONS_SERVICE_MISSION", required = false)
		var reservedOrganisationsServiceMission: Unit? = null,

		@field:Element(name = "RESTRICTED_SHELTERED_PROGRAM", required = false)
		var restrictedShelteredProgram: Unit? = null,

		@field:Element(name = "RESTRICTED_SHELTERED_WORKSHOP", required = false)
		var restrictedShelteredWorkshop: Unit? = null,

		@field:Element(name = "RULES_CRITERIA", required = false)
		@field:Convert(PConverter::class)
		var rulesCriteria: String? = null,

		@field:Element(name = "SUITABILITY", required = false)
		@field:Convert(PConverter::class)
		var suitability: String? = null,

		@field:Element(name = "TECHNICAL_CRITERIA_DOC", required = false)
		var technicalCriteriaDoc: Unit? = null,

		@field:Element(name = "TECHNICAL_PROFESSIONAL_INFO", required = false)
		@field:Convert(PConverter::class)
		var technicalProfessionalInfo: String? = null,

		@field:Element(name = "TECHNICAL_PROFESSIONAL_MIN_LEVEL", required = false)
		@field:Convert(PConverter::class)
		var technicalProfessionalMinLevel: String? = null
)