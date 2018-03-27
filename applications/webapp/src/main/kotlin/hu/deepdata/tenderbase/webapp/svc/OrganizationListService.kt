package hu.deepdata.tenderbase.webapp.svc

import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.webapp.repo.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.stereotype.*

/**
 * @author Zsolt Jurányi
 */
@Service
class OrganizationListService {

	@Autowired
	var repo: WOrganizationRepo? = null

	fun queryOrganizations(filters: OrganizationFilters, pageable: Pageable): Page<WOrganization> {
		val pageRequest = PageRequest(pageable.pageNumber, pageable.pageSize, Sort.Direction.ASC, "name")

		filters.name = filters.name.trim().toLowerCase()
		val nameLike =
				if (filters.name.isEmpty()) "%"
				else filters.name.replace(Regex("^|\\s+|$"), "%")

		return repo!!.findByNameLike(nameLike, pageRequest)
	}
}