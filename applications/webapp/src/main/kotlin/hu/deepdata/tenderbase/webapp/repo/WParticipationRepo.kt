package hu.deepdata.tenderbase.webapp.repo

import hu.deepdata.tenderbase.webapp.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

/**
 * @author Zsolt Jurányi
 */
@Repository
interface WParticipationRepo : JpaRepository<WParticipation, String> {

	@Query("SELECT new hu.deepdata.tenderbase.webapp.model.NameAndCount(p.role, COUNT(p)) " +
			"FROM WParticipation p " +
			"WHERE p.wOrganizationId = ?1 " +
			"GROUP BY p.role")
	fun findRoleCountsByOrganization(wOrganizationId: String): List<NameAndCount>
}