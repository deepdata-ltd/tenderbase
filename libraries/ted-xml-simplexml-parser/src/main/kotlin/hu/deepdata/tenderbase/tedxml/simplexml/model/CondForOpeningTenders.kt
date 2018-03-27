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
data class CondForOpeningTenders(
		@field:Element(name = "DATE_OPENING_TENDERS")
		@field:Convert(DateConverter::class)
		var dateOpeningTenders: Date? = null,

		@field:Element(name = "INFO_ADD", required = false)
		@field:Convert(PConverter::class)
		var infoAdd: String? = null,

		@field:Element(name = "PLACE", required = false)
		@field:Convert(PConverter::class)
		var place: String? = null,

		@field:Element(name = "TIME_OPENING_TENDERS")
		var timeOpeningTenders: String? = null
)