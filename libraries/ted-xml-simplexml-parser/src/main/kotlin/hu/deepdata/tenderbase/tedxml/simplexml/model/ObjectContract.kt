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
data class ObjectContract(
		@field:Element(name = "CALCULATION_METHOD", required = false)
		@field:Convert(PConverter::class)
		var calculationMethod: String? = null,

		@field:Element(name = "CPV_MAIN", required = false)
		var cpvMain: CpvSet? = null,

		@field:Element(name = "DATE_PUBLICATION_NOTICE", required = false)
		@field:Convert(DateConverter::class)
		var datePublicationNotice: Date? = null,

		@field:Attribute(name = "ITEM", required = false)
		var item: Int? = null,

		@field:Element(name = "LOT_DIVISION", required = false)
		var lotDivision: LotNumbers? = null,

		@field:Element(name = "NO_LOT_DIVISION", required = false)
		var noLotDivision: Unit? = null,

		@field:ElementList(entry = "OBJECT_DESCR", inline = true, required = false)
		var objectDescrs: List<ObjectDescr> = mutableListOf(),

		@field:Element(name = "REFERENCE_NUMBER", required = false)
		var referenceNumber: String? = null,

		@field:Element(name = "SHORT_DESCR", required = false)
		@field:Convert(PConverter::class)
		var shortDescr: String? = null,

		@field:Element(name = "TITLE", required = false)
		@field:Convert(PConverter::class)
		var title: String? = null,

		@field:Element(name = "TYPE_CONTRACT", required = false)
		var typeContract: TypeContract? = null,

		@field:Element(name = "VAL_ESTIMATED_TOTAL", required = false)
		var valEstimatedTotal: Value? = null,

		@field:Element(name = "VAL_RANGE_TOTAL", required = false)
		var valRangeTotal: ValueRange? = null,

		@field:Element(name = "VAL_TOTAL", required = false)
		var valTotal: Value? = null
)