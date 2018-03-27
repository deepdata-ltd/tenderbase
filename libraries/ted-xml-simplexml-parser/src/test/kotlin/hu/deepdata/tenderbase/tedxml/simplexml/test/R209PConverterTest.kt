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

import hu.deepdata.tenderbase.tedxml.simplexml.model.*
import org.junit.Test
import kotlin.test.*

/**
 * @author Zsolt Jurányi
 */
class R209PConverterTest : AbstractParsingTest() {

	override fun config() = Config(
			resource = "R209/399645_2017.xml"
	)

	@Test
	fun testWithSinglelineText() {
		val t = tedExport.translationSection.mlTitles.firstOrNull { it.lg == LanguageValue.ES }
		assertNotNull(t)
		val e = "Convocatoria de licitación n.o Chafea/2016/Health/18 sobre el apoyo a la red de expertos en planificación y previsión de personal sanitario"
		assertEquals(e, t!!.tiText)
	}

	@Test
	fun testWithMultilineText() {
		val f = tedExport.formSection.forms.first { it.category == Category.ORIGINAL }
		val s = f.objectContracts[0].shortDescr
		assertEquals(2, s?.split("\n")?.size ?: 0)
	}
}