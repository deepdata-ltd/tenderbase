package hu.deepdata.tenderbase.webapp.ctrl

import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.webapp.svc.*
import mu.*
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
@RequestMapping("/releases/")
class ReleaseListController {

	companion object : KLogging()

	@Autowired
	var counts: IndexServce? = null

	@Autowired
	var service: ReleaseListService? = null

	@GetMapping("/")
	fun list(@ModelAttribute filters: ReleaseFilters, @PageableDefault(size = 10) pageable: Pageable): ModelAndView {
		var t = -System.currentTimeMillis()
		val releases = service!!.queryReleases(filters, pageable)
		logger.trace("queryReleases: ${t + System.currentTimeMillis()} ms")

		t = -System.currentTimeMillis()
		val availableCountries = service!!.getAvailableCountries()
		logger.trace("getAvailableCountries: ${t + System.currentTimeMillis()} ms")

		t = -System.currentTimeMillis()
		val availableTags = service!!.getAvailableTags()
		logger.trace("getAvailableTags: ${t + System.currentTimeMillis()} ms")

		return ModelAndView("release-list", mapOf(
				"countriesAvailable" to availableCountries,
				"filters" to filters,
				"pageLinkPrefix" to "/releases/?$filters&page=",
				"releaseCount" to counts?.releaseCount(),
				"releases" to releases,
				"tagsAvailable" to availableTags
		))
	}
}