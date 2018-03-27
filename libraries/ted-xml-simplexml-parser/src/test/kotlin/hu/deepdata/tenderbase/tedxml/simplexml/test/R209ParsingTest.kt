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
import java.text.*
import kotlin.test.*

/**
 * @author Zsolt Jurányi
 */
class R209ParsingTest : AbstractParsingTest() {

	override fun config() = Config(
			resource = "R209/399643_2017.xml",
			// content
			datePub = SimpleDateFormat("yyyyMMdd").parse("20171011"),
			directive = "2014/24/EU",
			docId = "399643-2017",
			dtDateForSubmission = SimpleDateFormat("yyyyMMdd HH:mm").parse("20171116 13:00"),
			duration = Duration(DurationUnit.MONTH, 9),
			isoCountry = "ES",
			maMainActivities = listOf(TextAndCode("S", "General public services")),
			lgOrig = listOf(LanguageValue.ES),
			originalCpv = listOf(TextAndCode("45343000", "Fire-prevention installation works")),
			tdDocumentType = TextAndCode("3", "Contract notice"),
			values = listOf(Value(Currency.EUR, ValueType.ESTIMATED_TOTAL, 250000.0)),
			version = "R2.0.9.S02.E01"
	)

	@Test
	fun forms() {
		tedExport.formSection.forms.forEachIndexed { index, form ->
			assertEquals(
					if (3 == index) Category.ORIGINAL else Category.TRANSLATION,
					form.category
			)
			if (3 == index) assertEquals(LanguageValue.ES, form.lg)
		}
	}
}