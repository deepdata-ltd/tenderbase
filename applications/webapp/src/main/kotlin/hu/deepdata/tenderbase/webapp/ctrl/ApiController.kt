package hu.deepdata.tenderbase.webapp.ctrl

import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.webapp.repo.*
import hu.deepdata.tenderbase.webapp.svc.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.data.web.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

/**
 * @author Zsolt Jurányi
 */
@RestController
@RequestMapping("/api", produces = [MediaType.APPLICATION_JSON_VALUE])
class ApiController {

	// TODO "/record/{ocid}" w/ generated record model???

	@Autowired
	var organizationService: OrganizationService? = null

	@Autowired
	var organizationListService: OrganizationListService? = null

	@Autowired
	var releaseListService: ReleaseListService? = null

	@Autowired
	var wOrganizationRepo: WOrganizationRepo? = null

	@Autowired
	var wReleaseRepo: WReleaseRepo? = null

	fun error(e: Exception): ResponseEntity<String?> {
		return ResponseEntity("Internal error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
	}

	@GetMapping("organization/{id}")
	fun organization(@PathVariable("id") id: String): ResponseEntity<String?> {
		return try {
			val body = wOrganizationRepo!!.findOne(id)?.json
			ResponseEntity(body, when {
				body.isNullOrBlank() -> HttpStatus.NOT_FOUND
				else -> HttpStatus.OK
			})
		} catch (e: Exception) {
			error(e)
		}
	}

	@GetMapping("organization/{id}/releases/")
	fun organizationReleases(@PathVariable("id") id: String,
	                         @RequestParam(name = "role", required = false) role: String?,
	                         @PageableDefault(size = 10) pageable: Pageable): ResponseEntity<String?> {
		return try {
			val model = OrganizationModel(Organization().apply { this.id = id }, role, pageable)
			organizationService!!.queryReleasesByOrganizationAndRole(model)
			val sameRole = model.role?.toLowerCase() == role?.toLowerCase()
			val list =
					if (sameRole) model.releases?.mapNotNull(WRelease::json)?.joinToString(",") ?: ""
					else ""
			val body = "[$list]"
			ResponseEntity(body, when {
				list.isEmpty() -> HttpStatus.NOT_FOUND
				else -> HttpStatus.OK
			})
		} catch (e: Exception) {
			error(e)
		}
	}

	@GetMapping("organizations/")
	fun organizations(@ModelAttribute filters: OrganizationFilters, @PageableDefault(size = 10) pageable: Pageable): ResponseEntity<String?> {
		return try {
			val list = organizationListService!!.queryOrganizations(filters, pageable)
					.mapNotNull(WOrganization::json)
					.joinToString(",")
			val body = "[$list]"
			ResponseEntity(body, when {
				list.isEmpty() -> HttpStatus.NOT_FOUND
				else -> HttpStatus.OK
			})
		} catch (e: Exception) {
			error(e)
		}
	}

	@GetMapping("process/{id}")
	fun process(@PathVariable("id") id: String): ResponseEntity<String?> {
		return try {
			val list = wReleaseRepo!!.findByOcid(id)
					.mapNotNull(WRelease::json)
					.joinToString(",")
			val body = "[$list]"
			ResponseEntity(body, when {
				list.isEmpty() -> HttpStatus.NOT_FOUND
				else -> HttpStatus.OK
			})
		} catch (e: Exception) {
			error(e)
		}
	}

	@GetMapping("release/{id}")
	fun release(@PathVariable("id") id: String): ResponseEntity<String?> {
		return try {
			val body = wReleaseRepo!!.findOne(id)?.json
			return ResponseEntity(body, when {
				body.isNullOrBlank() -> HttpStatus.NOT_FOUND
				else -> HttpStatus.OK
			})
		} catch (e: Exception) {
			error(e)
		}
	}

	@GetMapping("releases/")
	fun releases(@ModelAttribute filters: ReleaseFilters, @PageableDefault(size = 10) pageable: Pageable): ResponseEntity<String?> {
		return try {
			val list = releaseListService!!.queryReleases(filters, pageable)
					.mapNotNull(WRelease::json)
					.joinToString(",")
			val body = "[$list]"
			return ResponseEntity(body, when {
				list.isEmpty() -> HttpStatus.NOT_FOUND
				else -> HttpStatus.OK
			})
		} catch (e: Exception) {
			error(e)
		}
	}
}