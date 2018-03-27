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
data class AwardContract(
		@field:Element(name = "AWARDED_CONTRACT", required = false)
		var awardedContract: AwardedContract? = null,

		@field:Element(name = "CONTRACT_NO", required = false)
		var contractNo: String? = null,

		@field:Attribute(name = "ITEM", required = false)
		var item: Int = 0,

		@field:Element(name = "LOT_NO", required = false)
		var lotNo: String? = null,

		@field:Element(name = "NO_AWARDED_CONTRACT", required = false)
		var noAwardedContract: NoAward? = null,

		@field:Element(name = "P", required = false)
		@field:Path("TITLE")
		@field:Convert(PConverter::class)
		var title: String? = null
)