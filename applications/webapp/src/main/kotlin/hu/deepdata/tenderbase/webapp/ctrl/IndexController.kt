package hu.deepdata.tenderbase.webapp.ctrl

import hu.deepdata.tenderbase.webapp.svc.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.*

/**
 * @author Zsolt Jurányi
 */
@Controller
class IndexController {

	@Autowired
	var svc: IndexServce? = null

	@GetMapping("/")
	fun index() = ModelAndView("index", mapOf(
			"latest" to svc?.latest(5),
			"organizationCount" to svc?.organizationCount(),
			"releaseCount" to svc?.releaseCount(),
			"tenderCount" to svc?.tenderCount(),
			"sumOfAwardedMillionEur" to (svc?.sumOfAwardedMillionEur() ?: 0)
	))

	@GetMapping("/resources", "/resources/")
	fun resources() = "resources"
}