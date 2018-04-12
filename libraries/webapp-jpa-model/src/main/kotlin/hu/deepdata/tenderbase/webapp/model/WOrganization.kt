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

import hu.deepdata.tenderbase.webapp.model.WOrganization.Companion.TABLE_NAME
import javax.persistence.*

/**
 * @author Zsolt Jurányi
 */
@Entity
@Table(name = TABLE_NAME
		// FULLTEXT indexes will be added by DatabasePreparationService for these fields:
		// `name`
)
data class WOrganization(
		@Id
		var id: String? = null,

		@Lob
		var json: String? = null,

		@Lob
		var name: String? = null

) {
	companion object {
		const val TABLE_NAME = "w_organization"
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is WOrganization) return false

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id?.hashCode() ?: 0
	}
}