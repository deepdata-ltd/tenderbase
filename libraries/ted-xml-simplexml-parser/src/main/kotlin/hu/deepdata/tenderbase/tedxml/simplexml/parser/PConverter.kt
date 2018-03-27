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

import org.simpleframework.xml.convert.*
import org.simpleframework.xml.stream.*

/**
 * @author Zsolt Jurányi
 */
class PConverter : Converter<String> {

	// Handles multiple <P>-s too!

	override fun read(pNode: InputNode): String {
		val s = StringBuilder()
		s.append(pNode.value ?: "")
		var n = pNode.next
		while (n != null) {
			// n shoud be an FT node btw, with TYPE attr (values: SUB, SUP)
			// but we only need its text
			// in fact, we may do recursion too, but it's unnecessary now
			s.append(n.value ?: "")
			s.append(pNode.value ?: "")
			n = pNode.next
		}
		return s.toString().replace(Regex("\n([ \t])+"), "\n").trim()
	}

	override fun write(n: OutputNode?, e: String?) {
		throw UnsupportedOperationException()
	}
}