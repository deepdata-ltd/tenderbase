package hu.deepdata.tenderbase.webapp.ctrl

import com.fasterxml.jackson.databind.*
import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.webapp.repo.*
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
@RequestMapping("/organization/")
class OrganizationController {

	companion object {
		val mapper = ObjectMapper()
	}

	@Autowired
	var wOrganizationRepo: WOrganizationRepo? = null

	@Autowired
	var organizationService: OrganizationService? = null

	@GetMapping("{id}")
	fun organization(@PathVariable("id") id: String,
	                 @RequestParam(name = "role", required = false) role: String?,
	                 @PageableDefault(size = 10) pageable: Pageable): ModelAndView {
		val wOrganization = wOrganizationRepo?.findOne(id)
		val organization = toOrganization(wOrganization?.json)
				?.apply { contactPoint = null } // don't need it on the page

		// TODO 404 if either of above is null

		val model = OrganizationModel(organization!!, role, pageable)
		organizationService?.queryReleasesByOrganizationAndRole(model)
		return ModelAndView("organization", mapOf(
				"listedRole" to model.role,
				"organization" to organization,
				"pageLinkPrefix" to "/organization/$id/releases/?role=${model.role}&page=",
				"releases" to model.releases,
				"roles" to model.roles
		))
	}

	fun toOrganization(json: String?) = if (null == json) null else
		mapper.readValue(json, Organization::class.java)

}