package hu.deepdata.tenderbase.webapp.svc

import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.webapp.repo.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.stereotype.*
import java.text.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
@Service
class ReleaseListService {

	@Autowired
	var cache: WCacheRepo? = null

	@Autowired
	var repo: WReleaseRepo? = null

	fun getAvailableCountries() = repo!!.findDistinctCountries().sorted()

	fun getAvailableTags() = cache!!.getOne(WCache.AVAILABLE_TAGS)?.value?.split(",")

	fun queryMaxDate() = repo!!.findFirstByOrderByPublishedAtDesc()?.publishedAt ?: Date()

	fun queryMinDate() = repo!!.findFirstByOrderByPublishedAtAsc()?.publishedAt ?: GregorianCalendar(2000, 1, 1).time

	fun queryReleases(filters: ReleaseFilters, pageable: Pageable): Page<WRelease> {
		val pageRequest = PageRequest(pageable.pageNumber, pageable.pageSize, Sort.Direction.DESC, "publishedAt", "id")


		with(filters) {
			// cleaning
			buyer = buyer.trim().toLowerCase()
			country = country.trim().toUpperCase() // country code!
			supplier = supplier.trim().toLowerCase()
			tag = tag.trim()
			title = title.trim().toLowerCase()

			if (minValue != null && maxValue != null && (minValue ?: 0) > (maxValue
							?: 0)) maxValue = minValue.also { minValue = maxValue }

			// preparing
			val buyerLike = toLike(buyer)
			val countryLike = if (country.isBlank()) "%" else country
			val supplierLike = toLike(supplier)
			val tagLike = if (filters.tag.isEmpty()) "%" else "%${filters.tag},%"
			val titleLike = toLike(title)

			val df = SimpleDateFormat("yyyy-MM-dd")
			if (maxDate.isBlank()) maxDate = df.format(queryMaxDate())
			if (minDate.isBlank()) minDate = df.format(queryMinDate())

			var dates = toDates(filters.date)
			if (2 != dates.size) dates = toDates("${filters.minDate} - ${filters.maxDate}")

			val value1 = filters.minValue ?: Long.MIN_VALUE
			val value2 = filters.maxValue ?: Long.MAX_VALUE

			return if (supplier.isEmpty()) {
				repo!!.findByDateAndTextsAndValue(
						dates[0], dates[1],
						countryLike, buyerLike, tagLike, titleLike,
						value1, value2,
						pageRequest)
			} else {
				repo!!.findByDateAndTextsAndValueAndSupplier(
						dates[0], dates[1],
						countryLike, buyerLike, tagLike, titleLike,
						value1, value2,
						supplierLike,
						pageRequest)
			}
		}
	}

	private fun toDates(dateFilter: String) = dateFilter
			.split(" - ")
			.filter { it.matches(Regex("\\d{4}-\\d{2}-\\d{2}")) }
			.map { SimpleDateFormat("yyyy-MM-dd").parse(it) }

	private fun toLike(s: String) =
			if (s.isEmpty()) "%"
			else s.replace(Regex("^|\\s+|$"), "%")
}