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

package hu.deepdata.tenderbase.tedxml.importer.conf

import org.apache.log4j.*
import org.springframework.boot.context.properties.*
import org.springframework.stereotype.*
import java.io.*

/**
 * @author Zsolt Jurányi
 */
@Component
@ConfigurationProperties(prefix = "import")
data class TxiConfiguration(
		var countryFilter: String? = null,
		var dailyPackagesDir: File? = File("daily-packages"),
		var logLevel: Level = Level.INFO,
		var ocidPrefix: String? = null,
		var reprocessPolicy: ReprocessPolicy = ReprocessPolicy.NO
)