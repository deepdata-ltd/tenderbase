package hu.deepdata.tenderbase.webapp.repo

import hu.deepdata.tenderbase.webapp.model.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

/**
 * @author Zsolt Jurányi
 */
@Repository
interface WOrganizationRepo : JpaRepository<WOrganization, String> {

	@Query("SELECT o FROM WOrganization o WHERE name LIKE ?1")
	fun findByNameLike(name: String, pageable: Pageable): Page<WOrganization>
}