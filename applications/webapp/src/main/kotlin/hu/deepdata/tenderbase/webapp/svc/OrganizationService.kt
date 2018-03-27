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
class OrganizationService {

	@Autowired
	var wParticipationRepo: WParticipationRepo? = null

	@Autowired
	var wReleaseRepo: WReleaseRepo? = null

	fun queryReleasesByOrganizationAndRole(model: OrganizationModel) {
		model.apply {
			roles = wParticipationRepo?.findRoleCountsByOrganization(organization.id) ?: listOf()
			val roleNames = roles.map(NameAndCount::name)
			role = if (null == role || !roleNames.contains(role!!)) roleNames.firstOrNull() ?: "" else role
			val pageRequest = PageRequest(pageable.pageNumber, pageable.pageSize, Sort.Direction.DESC, "publishedAt", "id")
			releases =
					if (role.isNullOrBlank()) null
					else wReleaseRepo?.findByOrganizationAndRole(organization.id, role!!, pageRequest)
		}
	}
}