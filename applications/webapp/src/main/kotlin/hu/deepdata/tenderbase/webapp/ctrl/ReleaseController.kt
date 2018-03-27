package hu.deepdata.tenderbase.webapp.ctrl

import com.fasterxml.jackson.databind.*
import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.webapp.repo.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.*

/**
 * @author Zsolt Jurányi
 */
@Controller
@RequestMapping("/release/")
class ReleaseController {

	companion object {
		val mapper = ObjectMapper()
	}

	@Autowired
	var repo: WReleaseRepo? = null

	@GetMapping("{id}")
	fun release(@PathVariable("id") id: String): ModelAndView {
		val wRelease = repo?.findOne(id)
		// TODO 404
		val ocdsRelease = toOcds(wRelease)
		val map = mutableMapOf(
				"release" to ocdsRelease,
				"id" to id,
				"json" to toPrettyJson(ocdsRelease)
		)
		if (null != wRelease?.ocid) {
			val wReleases = repo?.findByOcid(wRelease.ocid!!)
			map.put("releases", wReleases)
		}
		return ModelAndView("release", map)
	}

	fun toPrettyJson(ocdsRelease: ReleaseSchema?) = if (null == ocdsRelease) null else
		mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ocdsRelease)

	fun toOcds(wRelease: WRelease?) = if (null == wRelease) null else
		mapper.readValue(wRelease.json, ReleaseSchema::class.java)

}