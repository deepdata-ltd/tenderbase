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
data class ObjectDescr(
		// TODO F22: <QS> - need example

		@field:Element(name = "AC", required = false)
		var ac: Ac? = null,

		@field:ElementList(entry = "AC_COST", inline = true, required = false)
		var acCosts: List<AcDefinition> = mutableListOf(),

		@field:ElementList(entry = "AC_CRITERION", inline = true, required = false)
		var acCriterions: List<String> = mutableListOf(),

		@field:Element(name = "AC_PRICE", required = false)
		var acPrice: AcDefinition? = null,

		@field:Element(name = "AC_PROCUREMENT_DOC", required = false)
		var acProcurementDoc: Unit? = null,

		@field:ElementList(entry = "AC_QUALITY", inline = true, required = false)
		var acQualitys: List<AcDefinition> = mutableListOf(),

		@field:Element(name = "ACCEPTED_VARIANTS", required = false)
		var acceptedVariants: Unit? = null,

		@field:ElementList(entry = "CPV_ADDITIONAL", inline = true, required = false)
		var cpvAdditionals: List<CpvSet> = mutableListOf(),

		@field:Element(name = "CRITERIA_CANDIDATE", required = false)
		@field:Convert(PConverter::class)
		var criteriaCandidate: String? = null,

		@field:Element(name = "DATE_END", required = false)
		@field:Convert(DateConverter::class)
		var dateEnd: Date? = null,

		@field:Element(name = "DATE_START", required = false)
		@field:Convert(DateConverter::class)
		var dateStart: Date? = null,

		@field:Element(name = "DIRECTIVE_2009_81_EC", required = false)
		var directive200981EC: Ac? = null,

		@field:Element(name = "DIRECTIVE_2014_23_EU", required = false)
		var directive201423EU: Ac? = null,

		@field:Element(name = "DIRECTIVE_2014_24_EU", required = false)
		var directive201424EU: Ac? = null,

		@field:Element(name = "DIRECTIVE_2014_25_EU", required = false)
		var directive201425EU: Ac? = null,

		@field:Element(name = "DURATION", required = false)
		var duration: Duration? = null,

		@field:Element(name = "ECATALOGUE_REQUIRED", required = false)
		var ecatalogueRequired: Unit? = null,

		@field:Element(name = "EU_PROGR_RELATED", required = false)
		var euProgrRelated: String? = null,

		@field:Element(name = "INDEFINITE_DURATION", required = false)
		var indefiniteDuration: Unit? = null,

		@field:Element(name = "INFO_ADD", required = false)
		@field:Convert(PConverter::class)
		var infoAdd: String? = null,

		@field:Element(name = "JUSTIFICATION", required = false)
		var justification: String? = null,

		@field:Attribute(name = "ITEM", required = false)
		var item: Int? = null,

		@field:Element(name = "LOT_NO", required = false)
		var lotNo: String? = null,

		@field:Element(name = "MAIN_SITE", required = false)
		@field:Convert(PConverter::class)
		var mainSite: String? = null,

		@field:Element(name = "NB_ENVISAGED_CANDIDATE", required = false)
		@field:Convert(IntConverter::class)
		var nbEnvisagedCandidate: Int? = null,

		@field:Element(name = "NB_MAX_LIMIT_CANDIDATE", required = false)
		@field:Convert(IntConverter::class)
		var nbMaxLimitCandidate: Int? = null,

		@field:Element(name = "NB_MIN_LIMIT_CANDIDATE", required = false)
		@field:Convert(IntConverter::class)
		var nbMinLimitCandidate: Int? = null,

		@field:Element(name = "NO_ACCEPTED_VARIANTS", required = false)
		var noAcceptedVariants: Unit? = null,

		@field:Element(name = "NO_EU_PROGR_RELATED", required = false)
		var noEuProgrRelated: Unit? = null,

		@field:Element(name = "NO_OPTIONS", required = false)
		var noOptions: Unit? = null,

		@field:Element(name = "NO_RENEWAL", required = false)
		var noRenewal: Unit? = null,

		@field:ElementList(entry = "NUTS", inline = true, required = false)
		var nuts: List<TextAndCode> = mutableListOf(),

		@field:Element(name = "OPTIONS", required = false)
		var options: Unit? = null,

		@field:Element(name = "OPTIONS_DESCR", required = false)
		@field:Convert(PConverter::class)
		var optionsDescr: String? = null,

		@field:Element(name = "RENEWAL", required = false)
		var renewal: Unit? = null,

		@field:Element(name = "RENEWAL_DESCR", required = false)
		@field:Convert(PConverter::class)
		var renewalDescr: String? = null,

		@field:Element(name = "SHORT_DESCR", required = false)
		@field:Convert(PConverter::class)
		var shortDescr: String? = null,

		@field:Element(name = "TITLE", required = false)
		@field:Convert(PConverter::class)
		var title: String? = null,

		@field:Element(name = "VAL_OBJECT", required = false)
		var valObject: Value? = null
)