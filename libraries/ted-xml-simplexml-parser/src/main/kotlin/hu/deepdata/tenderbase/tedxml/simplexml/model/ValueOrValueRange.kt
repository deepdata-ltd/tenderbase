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
abstract class ValueOrValueRange(
		@field:Attribute(name = "CURRENCY")
		var currency: Currency? = null,

		@field:Attribute(name = "TYPE", required = false)
		var type: ValueType? = null
) {

	fun asSingleValues(): List<Value> =
			if (isSingleValue()) listOf(this as Value) else {
				val r = this as ValueRange
				listOf(
						Value(this.currency, this.type, r.low),
						Value(this.currency, this.type, r.high)
				)
			}

	fun asValueRange(): ValueRange =
			if (isValueRange()) this as ValueRange else {
				val v = this as Value
				ValueRange(v.currency, v.type, v.value, v.value)
			}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is ValueOrValueRange) return false

		if (currency != other.currency) return false
		if (type != other.type) return false

		return true
	}

	override fun hashCode(): Int {
		var result = currency?.hashCode() ?: 0
		result = 31 * result + (type?.hashCode() ?: 0)
		return result
	}

	fun isSingleValue() = this is Value

	fun isValueRange() = this is ValueRange

}
