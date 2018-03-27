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

package hu.deepdata.tenderbase.tedxml.importer.model

import hu.deepdata.tenderbase.webapp.model.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

/**
 * @author Zsolt Jurányi
 */
@Repository
interface WCacheRepo : JpaRepository<WCache, String> {

	@Query("SELECT COUNT(DISTINCT ocid) FROM WRelease wr")
	fun countOfTenders(): Long

	@Query("SELECT wr FROM WRelease wr WHERE CONCAT(tags,',') LIKE ?1")
	fun findByTagsLike(tags: String, pageable: Pageable): Page<WRelease>

	@Query("SELECT ROUND(SUM(eurValue/1000000)) FROM WRelease wr WHERE CONCAT(tags,',') LIKE '%award%'")
	fun sumOfAwardedMillionEur(): Long
}