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

package hu.deepdata.tenderbase.tedxml.simplexml.test

import org.junit.Test
import kotlin.test.*

/**
 * @author Zsolt Jurányi
 */
class R209TransliteratedAddrTest : AbstractParsingTest() {

	override fun config() = Config(
			resource = "R209/399809_2017.xml"
	)

	@Test
	fun test() {
		val n = tedExport.translationSection.transliteratedAddr?.nuts?.code
		assertEquals("BG343", n)

		val t = tedExport.translationSection.transliteratedAddr?.town
		assertEquals("Yambol", t)
	}
}