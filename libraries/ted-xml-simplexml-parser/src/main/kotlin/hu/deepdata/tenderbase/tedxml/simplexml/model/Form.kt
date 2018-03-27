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
data class Form(
		@field:ElementList(entry = "AWARD_CONTRACT", inline = true, required = false)
		var awardContracts: List<AwardContract> = mutableListOf(),

		@field:Attribute(name = "CATEGORY")
		var category: Category? = null,

		@field:Element(name = "CONTRACTING_BODY", required = false)
		var contractingBody: ContractingBody? = null,

		@field:Attribute(name = "FORM")
		var form: String = "",

		@field:Element(name = "LEFTI", required = false)
		var lefti: Lefti? = null,

		@field:Attribute(name = "LG")
		var lg: LanguageValue? = null,

		@field:ElementList(entry = "OBJECT_CONTRACT", inline = true, required = false)
		var objectContracts: List<ObjectContract> = mutableListOf(),

		@field:Element(name = "PROCEDURE", required = false)
		var procedure: Procedure? = null
)