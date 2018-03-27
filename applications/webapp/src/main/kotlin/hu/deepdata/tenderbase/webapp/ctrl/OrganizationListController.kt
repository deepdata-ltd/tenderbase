package hu.deepdata.tenderbase.webapp.ctrl

import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.webapp.svc.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.data.web.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.*

/**
 * @author Zsolt Jurányi
 */
@Controller
@RequestMapping("/organizations/")
class OrganizationListController {

	@Autowired
	var counts: IndexServce? = null

	@Autowired
	var service: OrganizationListService? = null

	@GetMapping("/")
	fun list(@ModelAttribute filters: OrganizationFilters, @PageableDefault(size = 10) pageable: Pageable): ModelAndView {
		val page = service!!.queryOrganizations(filters, pageable)
		return ModelAndView("organization-list", mapOf(
				"filters" to filters,
				"organizationCount" to counts?.organizationCount(),
				"organizations" to page,
				"pageLinkPrefix" to "/organizations/?$filters&page="
		))
	}
}