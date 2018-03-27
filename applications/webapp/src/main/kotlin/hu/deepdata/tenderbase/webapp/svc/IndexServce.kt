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
class IndexServce {

	@Autowired
	private var cache: WCacheRepo? = null

	@Autowired
	private var organizations: WOrganizationRepo? = null

	@Autowired
	private var releases: WReleaseRepo? = null

	fun latest(n: Int) = releases?.findAll(PageRequest(0, n, Sort.Direction.DESC, "id"))

	fun organizationCount() = organizations?.count()

	fun releaseCount() = releases?.count()

	fun tenderCount() = cache?.getOne(WCache.TENDER_COUNT)?.value?.toLong()

	fun sumOfAwardedMillionEur() = cache?.getOne(WCache.SUM_OF_AWARDED_MILLION_EUR)?.value?.toLong()
}