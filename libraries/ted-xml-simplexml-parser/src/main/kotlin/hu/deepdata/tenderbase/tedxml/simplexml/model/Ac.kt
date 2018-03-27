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
data class Ac(
		@field:ElementList(entry = "AC_COST", inline = true, required = false)
		var acCosts: List<AcDefinition> = mutableListOf(),

		@field:ElementList(entry = "AC_CRITERIA", inline = true, required = false) // DIRECTIVE_2009_81_EC
		var acCriterias: List<AcDefinition> = mutableListOf(),

		@field:ElementList(entry = "AC_CRITERION", inline = true, required = false) // DIRECTIVE_2014_23_EU
		var acCriterions: List<String> = mutableListOf(),

		@field:Element(name = "AC_PRICE", required = false)
		var acPrice: AcDefinition? = null,

		@field:ElementList(entry = "AC_QUALITY", inline = true, required = false)
		var acQualitys: List<AcDefinition> = mutableListOf()
)