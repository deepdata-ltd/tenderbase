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

package hu.deepdata.tenderbase.tedxml.importer

import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.tedxml.importer.model.*
import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.tedxml.importer.model.*
import mu.*
import org.springframework.data.domain.*

/**
 * @author Zsolt Jurányi
 */
class SlowCalculationCacher(private val repo: WCacheRepo) {

	companion object : KLogging()

	fun cacheSlowCalculations() {
		cacheAvailableTags()
		cacheCountOfTenders()
		cacheSumOfAwardedMillionEur()
	}

	private fun cacheAvailableTags() {
		logger.info("Calculating available tags")
		val tags = Tag.values().mapNotNull {
			val r = repo.findByTagsLike("%$it,%", PageRequest(0, 1)).firstOrNull()
			if (null == r) null else it.toString()
		}.sorted().joinToString(",")
		repo.saveAndFlush(WCache(WCache.AVAILABLE_TAGS, tags))
	}

	private fun cacheCountOfTenders() {
		logger.info("Calculating count of tenders")
		val count = repo.countOfTenders()
		repo.saveAndFlush(WCache(WCache.TENDER_COUNT, count.toString()))
	}

	private fun cacheSumOfAwardedMillionEur() {
		logger.info("Calculating sum of awarded million EURs")
		val sum = repo.sumOfAwardedMillionEur()
		repo.saveAndFlush(WCache(WCache.SUM_OF_AWARDED_MILLION_EUR, sum.toString()))
	}
}