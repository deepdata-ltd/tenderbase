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

package hu.deepdata.tenderbase.tedxml.importer.batch

import com.github.zafarkhaja.semver.*
import hu.deepdata.tenderbase.tedxml.importer.conf.*
import hu.deepdata.tenderbase.tedxml.importer.model.*

/**
 * @author Zsolt Jurányi
 */
open class UnprocessedMetaFilter(val policy: ReprocessPolicy, currentVersionStr: String) {

	private val p = parseVersion(currentVersionStr)

	open fun isProcessed(fileMeta: FileMeta): Boolean =
			0L == fileMeta.errors && null != fileMeta.processedAt && !shouldReprocess(fileMeta.processVersion)

	fun shouldReprocess(dataVersionStr: String?): Boolean {
		val d = parseVersion(dataVersionStr)
		return when (policy) {
			ReprocessPolicy.ALL -> true
			ReprocessPolicy.MAJOR -> d.majorVersion < p.majorVersion
			ReprocessPolicy.MINOR -> d.majorVersion < p.majorVersion
					|| (d.majorVersion == p.majorVersion && d.minorVersion < p.minorVersion)
			ReprocessPolicy.PATCH -> d.lessThan(p)
			ReprocessPolicy.NO -> false
		//else -> throw UnsupportedOperationException("ReprocessPolicy.$policy is not supported")
		}
	}

	private fun parseVersion(versionStr: String?) = try {
		Version.valueOf(versionStr)
	} catch (e: Exception) {
		Version.valueOf("0.0.0")
	}

}