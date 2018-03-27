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

package hu.deepdata.tenderbase.tedxml.importer.test

import hu.deepdata.tenderbase.tedxml.importer.batch.*
import hu.deepdata.tenderbase.tedxml.importer.conf.*
import org.junit.*
import org.junit.Assert.*

/**
 * @author Zsolt Jurányi
 */
class UnprocessedMetaFilterTest {

	@Test
	fun allMeansTrue() {
		val r = UnprocessedMetaFilter(ReprocessPolicy.ALL, "1.2.3")
		assertTrue(r.shouldReprocess("0.9.0"))
		assertTrue(r.shouldReprocess("1.0.0"))
		assertTrue(r.shouldReprocess("1.2.0"))
		assertTrue(r.shouldReprocess("1.2.2"))
		assertTrue(r.shouldReprocess("1.2.3"))
		assertTrue(r.shouldReprocess("1.2.4"))
		assertTrue(r.shouldReprocess("1.3.0"))
		assertTrue(r.shouldReprocess("2.0.0"))
	}

	@Test
	fun majorMeansTrueIfMajorIsLower() {
		val r = UnprocessedMetaFilter(ReprocessPolicy.MAJOR, "1.2.3")
		assertTrue(r.shouldReprocess("0.9.0"))
		assertFalse(r.shouldReprocess("1.0.0"))
		assertFalse(r.shouldReprocess("1.2.0"))
		assertFalse(r.shouldReprocess("1.2.2"))
		assertFalse(r.shouldReprocess("1.2.3"))
		assertFalse(r.shouldReprocess("1.2.4"))
		assertFalse(r.shouldReprocess("1.3.0"))
		assertFalse(r.shouldReprocess("2.0.0"))
	}

	@Test
	fun minorMeansTrueIfMajorOrMinorIsLower() {
		val r = UnprocessedMetaFilter(ReprocessPolicy.MINOR, "1.2.3")
		assertTrue(r.shouldReprocess("0.9.0"))
		assertTrue(r.shouldReprocess("1.0.0"))
		assertFalse(r.shouldReprocess("1.2.0"))
		assertFalse(r.shouldReprocess("1.2.2"))
		assertFalse(r.shouldReprocess("1.2.3"))
		assertFalse(r.shouldReprocess("1.2.4"))
		assertFalse(r.shouldReprocess("1.3.0"))
		assertFalse(r.shouldReprocess("2.0.0"))
	}

	@Test
	fun patchMeansTrueIfAllIsLower() {
		val r = UnprocessedMetaFilter(ReprocessPolicy.PATCH, "1.2.3")
		assertTrue(r.shouldReprocess("0.9.0"))
		assertTrue(r.shouldReprocess("1.0.0"))
		assertTrue(r.shouldReprocess("1.2.0"))
		assertTrue(r.shouldReprocess("1.2.2"))
		assertFalse(r.shouldReprocess("1.2.3"))
		assertFalse(r.shouldReprocess("1.2.4"))
		assertFalse(r.shouldReprocess("1.3.0"))
		assertFalse(r.shouldReprocess("2.0.0"))
	}

	@Test
	fun noMeansFalse() {
		val r = UnprocessedMetaFilter(ReprocessPolicy.NO, "1.2.3")
		assertFalse(r.shouldReprocess("0.9.0"))
		assertFalse(r.shouldReprocess("1.0.0"))
		assertFalse(r.shouldReprocess("1.2.0"))
		assertFalse(r.shouldReprocess("1.2.2"))
		assertFalse(r.shouldReprocess("1.2.3"))
		assertFalse(r.shouldReprocess("1.2.4"))
		assertFalse(r.shouldReprocess("1.3.0"))
		assertFalse(r.shouldReprocess("2.0.0"))
	}
}