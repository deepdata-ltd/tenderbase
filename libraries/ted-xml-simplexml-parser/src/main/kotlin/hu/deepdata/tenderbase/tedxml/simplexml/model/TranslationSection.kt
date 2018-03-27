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
data class TranslationSection(
		@field:ElementList(name = "ML_AA_NAMES", entry = "AA_NAME")
		var mlAaNames: List<AaName> = mutableListOf(),

		@field:ElementList(name = "ML_TITLES", entry = "ML_TI_DOC")
		var mlTitles: List<MlTiDoc> = mutableListOf(),

		@field:Element(name = "TRANSLITERATED_ADDR", required = false)
		@field:Path("TRANSLITERATIONS")
		var transliteratedAddr: ContactContractingBody? = null
)