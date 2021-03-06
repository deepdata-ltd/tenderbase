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

package hu.deepdata.tenderbase.tedxml.simplexml.parser

import hu.deepdata.tenderbase.tedxml.simplexml.model.*
import org.simpleframework.xml.convert.*
import org.simpleframework.xml.stream.*

/**
 * @author Zsolt Jurányi
 */
class DurationConverter : Converter<Duration> {

	override fun read(node: InputNode) = try {
		Duration().apply {
			this.type = DurationUnit.valueOf(node.getAttribute("TYPE").value)
			this.value = node.value.trim().toInt()
		}
	} catch (e: Exception) {
		null
	}

	override fun write(n: OutputNode, d: Duration?) {
		throw UnsupportedOperationException()
	}

}