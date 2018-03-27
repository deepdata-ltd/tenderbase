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
data class Pt(
		@field:Element(name = "PT_AWARD_CONTRACT_WITHOUT_CALL", required = false)
		var ptAwardContractWithoutCall: Annex? = null,

		@field:Element(name = "PT_AWARD_CONTRACT_WITHOUT_PUBLICATION", required = false)
		var ptAwardContractWithoutPublication: Annex? = null,

		@field:Element(name = "PT_NEGOTIATED_WITHOUT_PUBLICATION", required = false)
		var ptNegotiatedWithoutPublication: Annex? = null
)