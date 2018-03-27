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

import hu.deepdata.tenderbase.webapp.model.*
import mu.*
import org.easybatch.core.record.*
import org.easybatch.core.writer.*
import org.hibernate.*
import org.springframework.beans.*

/**
 * Kotlin port of `easybatch-hibernate`'s `HibernateRecordWriter`, which can handle records with Collection as
 * payload: in that case each element will be persisted.
 *
 * Also, for some reason using EasyBatch's class causes the following error:
 *
 * `java.lang.NoSuchMethodError: org.hibernate.Session.close()Ljava/sql/Connection;`
 *
 * @author Zsolt Jurányi
 */
class CustomHibernateRecordWriter(private val sessionFactory: SessionFactory) : RecordWriter {

	companion object : KLogging()

	var session: Session? = null

	override fun writeRecords(batch: Batch) {
		val entities = batch.map { it.payload as? Collection<*> ?: listOf(it.payload) }.flatten()
		logger.debug("Storing ${entities.size} entities from ${batch.size()} batch records")
		val tx = session!!.transaction!!
		tx.begin()
		try {
			entities.forEach { e ->
				if (e is WOrganization) {
					// We need this block because WOrganizations can be redundant
					// even in the same batch and JPA cries if same ID appears in
					// different objects...
					val old = session!!.get(WOrganization::class.java, e.id)
					if (null == old) {
						session!!.save(e)
					} else {
						BeanUtils.copyProperties(e, old)
						session!!.saveOrUpdate(old)
					}
				} else {
					session!!.saveOrUpdate(e)
				}
			}
			session!!.flush()
			session!!.clear()
			tx.commit()
			logger.trace("Transaction committed")
		} catch (e: Exception) {
			logger.error("Could not store entities", e)
			tx.rollback()
			logger.trace("Transaction rolled back")
		}
	}

	override fun open() {
		session = sessionFactory.openSession()
	}

	override fun close() {
		session?.close()
	}
}