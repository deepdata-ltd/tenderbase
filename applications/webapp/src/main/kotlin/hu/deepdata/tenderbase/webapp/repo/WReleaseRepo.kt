package hu.deepdata.tenderbase.webapp.repo

import hu.deepdata.tenderbase.webapp.model.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

/**
 * @author Zsolt Jurányi
 */
@Repository
interface WReleaseRepo : JpaRepository<WRelease, String> {

	fun findByOcid(ocid: String): List<WRelease>

	@Query("SELECT wr FROM WRelease wr WHERE id IN (SELECT p.wReleaseId FROM WParticipation p " +
			"WHERE p.wOrganizationId = ?1 AND p.role = ?2)")
	fun findByOrganizationAndRole(wOrganizationId: String, role: String, pageable: Pageable): Page<WRelease>

	@Query("SELECT DISTINCT wr.buyerCountry FROM WRelease wr")
	fun findDistinctCountries(): List<String>

	fun findFirstByOrderByPublishedAtAsc(): WRelease?

	fun findFirstByOrderByPublishedAtDesc(): WRelease?
}