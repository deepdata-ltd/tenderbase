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

import hu.deepdata.tenderbase.tedxml.importer.batch.tar.*
import hu.deepdata.tenderbase.tedxml.importer.batch.xml.*
import hu.deepdata.tenderbase.tedxml.importer.conf.*
import hu.deepdata.tenderbase.tedxml.importer.conv.*
import hu.deepdata.tenderbase.tedxml.importer.model.*
import org.apache.log4j.*
import org.easybatch.core.filter.*
import org.easybatch.core.job.*
import org.easybatch.core.listener.*
import org.easybatch.core.reader.*
import org.easybatch.core.record.*
import org.easybatch.core.writer.*
import org.hibernate.*
import java.util.concurrent.*

/**
 * @author Zsolt Jurányi
 */
class TxiBatchContext(
		val currentVersionStr: String,
		val conf: TxiConfiguration,
		private val fileMetaRepo: FileMetaRepo,
		private val sessionFactory: SessionFactory
) {

	/**
	 * This queue is placed between TAR collector and TAR processor jobs. Record payloads should be [TarRecord] objects.
	 * @see TarRecord
	 */
	private val tarQueue = LinkedBlockingQueue<Record<*>?>()

	var unprocessedMetaFilter = UnprocessedMetaFilter(conf.reprocessPolicy!!, currentVersionStr)
	var tedExportConverter = TedExportConverter()
	var supportedCountryFilter = SupportedCountryFilter(conf.countryFilter)
	var ocdsConverterImpl = OcdsReleaseConverter(conf.ocidPrefix!!)
	var wReleaseConverter = WReleaseConverter()
	var wOrganizationsConverter = WOrganizationsConverter()
	var wParticipationsConverter = WParticipationsConverter()

	init {
		org.apache.log4j.Logger
				.getLogger("hu.deepdata.tenderbase.tedxml.importer")
				.level = conf.logLevel

		java.util.logging.Logger
				.getLogger("org.easybatch.core.job.BatchJob")
				.useParentHandlers = conf.logLevel == Level.TRACE || conf.logLevel == Level.DEBUG
	}

	private fun initJob(name: String): JobBuilder = JobBuilder.aNewJob().named(name).batchSize(100)

	fun buildTarCollectorJob(): Job =
			initJob("tar-collector")
					.reader(CustomFileRecordReader(conf.dailyPackagesDir!!))
					.filter(SupportedTarFilter())
					.mapper(TarRecordMapper())
					.processor(TarMetaFetcher(fileMetaRepo))
					.filter(UnprocessedTarFilter(unprocessedMetaFilter))
					.writer(BlockingQueueRecordWriter(tarQueue))
					.jobListener(PoisonRecordBroadcaster(listOf(tarQueue)))
					.build()

	fun buildTarProcessorJob(): Job =
			initJob("tar-processor")
					.reader(BlockingQueueRecordReader(tarQueue))
					.filter(PoisonRecordFilter())
					.processor(TarProcessor(this)) // runs XML jobs, manages error count
					.mapper(TarFinalizer())
					.processor(FileMetaSigner(currentVersionStr))
					.writer(CustomHibernateRecordWriter(sessionFactory))
					.build()

	fun buildXmlCollectorJob(tarRecord: TarRecord, xmlQueue: LinkedBlockingQueue<Record<*>>): Job =
			initJob("xml-collector/" + tarRecord.file.name)
					.reader(TarReader(tarRecord))
					.filter(SupportedXmlFilter())
					.processor(XmlMetaFetcher(fileMetaRepo))
					.filter(UnprocessedXmlFilter(unprocessedMetaFilter))
					.writer(BlockingQueueRecordWriter(xmlQueue))
					.jobListener(PoisonRecordBroadcaster(listOf(xmlQueue)))
					.build()

	fun buildXmlProcessorJob(tarRecord: TarRecord, xmlQueue: LinkedBlockingQueue<Record<*>>): Job =
			initJob("xml-processor/" + tarRecord.file.name)
					.reader(BlockingQueueRecordReader(xmlQueue))
					.filter(PoisonRecordFilter())
					.processor(TedExportMapper(tedExportConverter))
					.filter(supportedCountryFilter)
					.processor(TenderIdPreparation())
					.processor(TenderIdResolver(fileMetaRepo))
					.filter(TenderIdFilter())
					.processor(OcdsReleaseMapper(ocdsConverterImpl))
					.processor(WebappModelMapper(wReleaseConverter, wOrganizationsConverter, wParticipationsConverter))
					.processor(WordExtractor())
					.mapper(XmlFinalizer())
					.processor(FileMetaSigner(currentVersionStr))
					.writer(CustomHibernateRecordWriter(sessionFactory))
					.build()

	fun executeMultiple(vararg jobs: Job): JobReport {
		with(JobExecutor(jobs.size)) {
			val futures = submitAll(*jobs)
			shutdown()
			val reportMerger = DefaultJobReportMerger()
			return reportMerger.mergerReports(*futures.map(Future<JobReport>::get).toTypedArray())
		}
	}

	fun executeSingle(job: Job): JobReport {
		with(JobExecutor()) {
			val report = execute(job)
			shutdown()
			return report
		}
	}
}