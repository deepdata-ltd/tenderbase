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

package hu.deepdata.tenderbase.webapp.svc

import mu.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import java.sql.*
import javax.annotation.*
import javax.sql.*

/**
 * @author Zsolt Jurányi
 */
@Service
class DatabasePreparationService {

	companion object : KLogging()

	@Autowired
	var dataSource: DataSource? = null

	@PostConstruct
	fun addFulltextIndexes() {
		dataSource?.connection?.use { c ->
			addFulltextIndexes(c, "w_release", mapOf(
					"release_buyer_idx" to "buyer_name",
					"release_supplier_idx" to "supplier_names",
					"release_title_idx" to "title",
					"release_words_idx" to "words"
			))
			addFulltextIndexes(c, "w_organization", mapOf(
					"org_name_idx" to "name"
			))
		}
	}

	private fun addFulltextIndexes(connection: Connection, table: String, indexes: Map<String, String>) {
		indexes.forEach { index, field ->
			if (!isIndexPresent(connection, table, index))
				addFulltextIndex(connection, table, index, field)
		}
	}

	fun addFulltextIndex(connection: Connection, table: String, index: String, columns: String) {
		val sql = "alter table `$table` add fulltext index `$index` ($columns)"
		val stmt = connection.prepareStatement(sql)
		stmt.use {
			logger.debug("Adding index $table.$index ($columns)")
			it.executeUpdate()
		}
	}

	private fun isIndexPresent(connection: Connection, table: String, index: String): Boolean {
		val sql = "show index from `$table` where key_name = '$index'"
		val stmt = connection.prepareStatement(sql)
		stmt.use {
			it.executeQuery().use {
				val present = it.next()
				logger.debug("Index $table.$index presents: $present")
				return present
			}
		}
	}
}