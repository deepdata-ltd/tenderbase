package hu.deepdata.tenderbase.webapp.svc

import hu.deepdata.tenderbase.webapp.model.*
import hu.deepdata.tenderbase.webapp.repo.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.jdbc.core.*
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

	@Autowired
	var jdbc: JdbcTemplate? = null
	val table = WRelease.TABLE_NAME

	fun getAvailableCountries() = repo!!.findDistinctCountries().sorted()

	fun getAvailableTags() = cache!!.getOne(WCache.AVAILABLE_TAGS)?.value?.split(",")

	fun queryMaxDate() = repo!!.findFirstByOrderByPublishedAtDesc()?.publishedAt ?: Date()

	fun queryMinDate() = repo!!.findFirstByOrderByPublishedAtAsc()?.publishedAt ?: GregorianCalendar(2000, 1, 1).time

	fun queryReleases(filters: ReleaseFilters, pageable: Pageable): Page<WRelease> {
		val whereModel = generateConditions(filters)
		val whereSql = whereModel.toSqlString()
		val totalQuery = "SELECT COUNT(1) FROM $table $whereSql"
		val total = jdbc?.queryForObject(totalQuery, whereModel.arguments.toTypedArray(), Long::class.java) ?: 0L
		val orderSql = "ORDER BY published_at DESC, id DESC"
		val limitOffsetSql = "LIMIT ${pageable.pageSize} OFFSET ${pageable.offset}"
		val pageQuery = "SELECT * FROM $table $whereSql $orderSql $limitOffsetSql"
		val content = queryWReleases(pageQuery, whereModel.arguments.toTypedArray())
		return PageImpl<WRelease>(content, pageable, total)
	}

	private fun cleanFilters(filters: ReleaseFilters) {
		with(filters) {
			buyer = buyer.trim().toLowerCase()
			country = country.trim().toUpperCase() // country code!
			supplier = supplier.trim().toLowerCase()
			tag = tag.trim()
			title = title.trim().toLowerCase()

			// value is negative -> null it!
			if (maxValue ?: 0 <= 0) maxValue = null
			if (minValue ?: 0 <= 0) minValue = null

			// both non-null, max > min -> swap
			if (minValue != null && maxValue != null
					&& (minValue ?: 0) > (maxValue ?: 0))
				maxValue = minValue.also { minValue = maxValue }
		}
	}

	private fun generateConditions(filters: ReleaseFilters): WhereClause {
		val df = SimpleDateFormat("yyyy-MM-dd")
		if (filters.maxDate.isBlank()) filters.maxDate = df.format(queryMaxDate())
		if (filters.minDate.isBlank()) filters.minDate = df.format(queryMinDate())

		cleanFilters(filters)

		val dateFilter = filters.date.split(" - ").map {
			try {
				SimpleDateFormat("yyyy-MM-dd").parse(it)
			} catch (e: Exception) {
				null
			}
		}

		val minDate = if (dateFilter.size >= 1) dateFilter[0] else null
		val maxDate = if (dateFilter.size >= 2) dateFilter[1] else null

		val tagFilter = if (filters.tag.isEmpty()) "" else filters.tag + ","
		return WhereClause().apply {
			equal("COALESCE(buyer_country, '')", filters.country)
			matchAgainstBoolean("buyer_name", WhereClause.toBooleanQueryWithAnd(filters.buyer))
			like("CONCAT(tags, ',')", tagFilter)
			matchAgainstBoolean("supplier_names", WhereClause.toBooleanQueryWithAnd(filters.supplier))
			matchAgainstBoolean("title", WhereClause.toBooleanQueryWithAnd(filters.title))
			matchAgainstBoolean("words", WhereClause.toBooleanQueryWithAnd(filters.words))
			gte("COALESCE(eur_value, 0)", filters.minValue)
			lte("COALESCE(eur_value, 0)", filters.maxValue)
			gte("published_at", minDate)
			lte("published_at", maxDate)
		}
	}

	private fun parseDate(s: String?): Date? {
		if (null == s) return null
		return try {
			SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s)
		} catch (e: Exception) {
			null
		}
	}

	private fun queryWReleases(sql: String, args: Array<out Any>): List<WRelease> {
		return jdbc?.queryForList(sql, *args)?.map {
			WRelease().apply {
				buyerCountry = it["buyer_country"]?.toString()
				buyerName = it["buyer_name"]?.toString()
				eurValue = it["eur_value"]?.toString()?.toLong()
				id = it["id"]?.toString()
				json = it["json"]?.toString()
				ocid = it["ocid"]?.toString()
				publishedAt = parseDate(it["published_at"]?.toString())
				tags = it["tags"]?.toString()
				title = it["title"]?.toString()
				words = it["words"]?.toString()
			}
		} ?: listOf()
	}
}