package hu.deepdata.tenderbase.webapp.repo

import hu.deepdata.tenderbase.webapp.model.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
@Repository
interface WReleaseRepo : JpaRepository<WRelease, String> {

	@Query("SELECT wr FROM WRelease wr " +
			"WHERE publishedAt BETWEEN ?1 AND ?2 " +
			"AND COALESCE(buyerCountry, '') LIKE ?3 " +
			"AND COALESCE(buyerName, '') LIKE ?4 " +
			"AND CONCAT(tags, ',') LIKE ?5 " +
			"AND COALESCE(title, '') LIKE ?6 " +
			"AND COALESCE(eurValue, 0) BETWEEN ?7 AND ?8")
	fun findByDateAndTextsAndValue(d1: Date, d2: Date, country: String, buyer: String, tag: String, title: String, v1: Long, v2: Long, pageable: Pageable): Page<WRelease>

	@Query("SELECT wr FROM WRelease wr " +
			"WHERE publishedAt BETWEEN ?1 AND ?2 " +
			"AND COALESCE(buyerCountry, '') LIKE ?3 " +
			"AND COALESCE(buyerName, '') LIKE ?4 " +
			"AND CONCAT(tags, ',') LIKE ?5 " +
			"AND COALESCE(title, '') LIKE ?6 " +
			"AND COALESCE(eurValue, 0) BETWEEN ?7 AND ?8 " +
			"AND id IN (SELECT wReleaseId FROM WParticipation p " +
			"WHERE role='supplier' AND wOrganizationId IN (SELECT id FROM WOrganization o WHERE name LIKE ?9))")
	fun findByDateAndTextsAndValueAndSupplier(d1: Date, d2: Date, country: String, buyer: String, tag: String, title: String, v1: Long, v2: Long, supplier: String, pageable: Pageable): Page<WRelease>

	fun findByOcid(ocid: String): List<WRelease>

	@Query("SELECT wr FROM WRelease wr WHERE id IN (SELECT p.wReleaseId FROM WParticipation p " +
			"WHERE p.wOrganizationId = ?1 AND p.role = ?2)")
	fun findByOrganizationAndRole(wOrganizationId: String, role: String, pageable: Pageable): Page<WRelease>

	@Query("SELECT DISTINCT wr.buyerCountry FROM WRelease wr")
	fun findDistinctCountries(): List<String>

	fun findFirstByOrderByPublishedAtAsc(): WRelease?

	fun findFirstByOrderByPublishedAtDesc(): WRelease?
}