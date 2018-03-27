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

import java.util.*
import javax.persistence.*

/**
 * @author Zsolt Jurányi
 */
@Entity
@Table(name = "w_release", indexes = [
	(Index(name = "release_country_idx", columnList = "buyerCountry")),
	(Index(name = "release_buyer_idx", columnList = "buyerName")),
	(Index(name = "release_ocid_idx", columnList = "ocid")),
	(Index(name = "release_date_idx", columnList = "publishedAt")),
	(Index(name = "release_title_idx", columnList = "title")),
	(Index(name = "release_value_idx", columnList = "eurValue"))
])
data class WRelease(
		@Id
		var id: String? = null, // OCID prefix + tender ID + notice ID

		@Column(length = 2)
		var buyerCountry: String? = null,
		var buyerName: String? = null,
		var eurValue: Long? = null,

		@Lob
		var json: String? = null,

		var ocid: String? = null, // OCID prefix + tender ID
		var publishedAt: Date? = null,
		var tags: String? = null,
		var title: String? = null
)