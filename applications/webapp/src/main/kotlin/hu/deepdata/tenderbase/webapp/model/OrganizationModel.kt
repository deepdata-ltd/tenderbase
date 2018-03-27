package hu.deepdata.tenderbase.webapp.model

import hu.deepdata.tenderbase.ocds.jackson.*
import org.springframework.data.domain.*

/**
 * @author Zsolt Jurányi
 */
data class OrganizationModel(
		// input
		val organization: Organization,
		var role: String?,
		var pageable: Pageable,
		// output
		var releases: Page<WRelease>? = null,
		var roles: List<NameAndCount> = mutableListOf()
)