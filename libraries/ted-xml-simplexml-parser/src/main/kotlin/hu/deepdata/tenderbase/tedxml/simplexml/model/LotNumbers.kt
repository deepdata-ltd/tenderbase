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
data class LotNumbers(
		@field:Element(name = "LOT_ALL", required = false)
		var lotAll: Unit? = null,

		@field:Element(name = "LOT_COMBINING_CONTRACT_RIGHT", required = false)
		@field:Convert(PConverter::class)
		var lotCombiningContractRight: String? = null,

		@field:Element(name = "LOT_MAX_NUMBER", required = false)
		@field:Convert(IntConverter::class)
		var lotMaxNumber: Int? = null,

		@field:Element(name = "LOT_MAX_ONE_TENDERER", required = false)
		@field:Convert(IntConverter::class)
		var lotMaxOneTenderer: Int? = null,

		@field:Element(name = "LOT_ONE_ONLY", required = false)
		var lotOneOnly: Unit? = null
)