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

import java.util.*
import javax.persistence.*

/**
 * @author Zsolt Jurányi
 */
@Entity
@Table(indexes = arrayOf(
		Index(name = "idx_tar_id", columnList = "tarId"),
		Index(name = "idx_notice_id", columnList = "noticeId")
))
data class FileMeta(
		@Id
		var id: String? = null,

		var name: String? = null,
		var detectedAt: Date? = null,
		var errors: Long = 0L,
		var processedAt: Date? = null,
		var processVersion: String? = null,

		// xml data:
		var tarId: String? = null,
		var noticeId: String? = null,
		var refNoticeId: String? = null, // TED-XML referenced notices (~ parent notice)
		var tenderId: String? = null
)