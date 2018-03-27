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

import hu.deepdata.tenderbase.tedxml.importer.batch.*
import hu.deepdata.tenderbase.tedxml.importer.conf.*
import hu.deepdata.tenderbase.tedxml.importer.model.*
import org.hibernate.jpa.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.boot.autoconfigure.domain.*
import org.springframework.boot.context.event.*
import org.springframework.context.event.*

/**
 * Main class of the TED-XML Importer, which contains the main method as well as the initialization code. The task of
 * TED-XML Importer is to read daily packages downloaded from TED, parse the XMLs, convert the data to OCDS model and
 * store them into a database.
 *
 * @author Zsolt Jurányi
 */
@SpringBootApplication
@EntityScan(basePackages = arrayOf(
		"hu.deepdata.tenderbase.tedxml.importer.model",
		"hu.deepdata.tenderbase.webapp.model"
))
class TedXmlImporterApp {

	// TODO document all classes and functions

	companion object {

		/**
		 * Entry point of the application, it starts the Spring Boot Framework.
		 * @param args Command line arguments
		 */
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(TedXmlImporterApp::class.java, *args)
		}
	}

	@Autowired
	var conf = TxiConfiguration()

	@Autowired
	var fileMetaRepo: FileMetaRepo? = null

	@Autowired
	var hemf: HibernateEntityManagerFactory? = null

	@Value("\${app.version}")
	var version: String? = null

	@Autowired
	var wCacheRepo: WCacheRepo? = null

	@EventListener(ApplicationReadyEvent::class)
	@Suppress("unused", "UNNECESSARY_NOT_NULL_ASSERTION")
	fun startup() {
		with(TxiBatchContext(version!!, conf!!, fileMetaRepo!!, hemf!!.sessionFactory)) {
			executeMultiple(
					buildTarCollectorJob(),
					buildTarProcessorJob() // XML jobs called inside
			)
		}
		SlowCalculationCacher(wCacheRepo!!).cacheSlowCalculations()
	}
}