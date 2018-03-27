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

import org.apache.commons.lang3.builder.*
import org.simpleframework.xml.*

/**
 * @author Zsolt Jurányi
 */
class ValueRange(
		currency: Currency? = null,
		type: ValueType? = null,

		@field:Element(name = "HIGH", required = false) // TODO custom converter (cut spaces, .00-s, etc)
		var high: Double? = null,

		@field:Element(name = "LOW", required = false) // TODO custom converter (cut spaces, .00-s, etc)
		var low: Double? = null
) : ValueOrValueRange(currency, type) {

	override fun toString(): String = ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("currency", currency)
			.append("type", type)
			.append("low", low)
			.append("high", high)
			.build()
}
