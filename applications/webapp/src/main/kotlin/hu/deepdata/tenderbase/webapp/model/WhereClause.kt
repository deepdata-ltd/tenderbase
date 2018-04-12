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

package hu.deepdata.tenderbase.webapp.model

/**
 * @author Zsolt Jurányi
 */
data class WhereClause(
		val conditions: MutableList<String> = mutableListOf(),
		val arguments: MutableList<Any> = mutableListOf()
) {

	companion object {
		fun toBooleanQueryWithAnd(s: String) =
				s.replace("*", " ")
						.split(Regex("[ ,]"))
						.map(String::trim)
						.filterNot(String::isEmpty)
						.joinToString(" ") { "+$it*" }
	}

	fun equal(field: String, argument: Any?) {
		infix(field, "=", argument)
	}

	fun gte(field: String, argument: Any?) {
		infix(field, ">=", argument)
	}

	fun infix(field: String, operator: String, argument: Any?) {
		nullIfBlank(argument)?.also {
			conditions.add(" $field $operator ? ")
			arguments.add(it)
		}
	}

	fun like(field: String, argument: Any?) {
		infix(field, "LIKE", argument)
	}

	fun lte(field: String, argument: Any?) {
		infix(field, "<=", argument)
	}

	fun matchAgainstBoolean(field: String, argument: Any?) {
		nullIfBlank(argument)?.also {
			conditions.add(" MATCH ($field) AGAINST (? IN BOOLEAN MODE) ")
			arguments.add(it)
		}
	}

	private fun nullIfBlank(a: Any?) = if (a is String && a.isBlank()) null else a

	fun toSqlString() =
			if (conditions.isEmpty()) ""
			else " WHERE ${conditions.joinToString(" AND ")} "
					.replace(Regex(" +"), " ")
}