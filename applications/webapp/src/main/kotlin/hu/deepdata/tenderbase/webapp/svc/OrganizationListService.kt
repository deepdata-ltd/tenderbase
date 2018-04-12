package hu.deepdata.tenderbase.webapp.svc

import hu.deepdata.tenderbase.webapp.model.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.jdbc.core.*
import org.springframework.stereotype.*

/**
 * @author Zsolt Jurányi
 */
@Service
class OrganizationListService {

	@Autowired
	var jdbc: JdbcTemplate? = null
	val table = WOrganization.TABLE_NAME

	fun queryOrganizations(filters: OrganizationFilters, pageable: Pageable): Page<WOrganization> {
		filters.name = filters.name.trim().toLowerCase()
		val nameFilter = WhereClause.toBooleanQueryWithAnd(filters.name)
		val whereModel = WhereClause().apply { matchAgainstBoolean("name", nameFilter) }
		val whereSql = whereModel.toSqlString()
		val totalQuery = "SELECT COUNT(1) FROM $table $whereSql"
		val total = jdbc?.queryForObject(totalQuery, whereModel.arguments.toTypedArray(), Long::class.java) ?: 0L
		val orderSql = "ORDER BY name"
		val limitOffsetSql = "LIMIT ${pageable.pageSize} OFFSET ${pageable.offset}"
		val pageQuery = "SELECT * FROM $table $whereSql $orderSql $limitOffsetSql"
		val content = queryWOrganizations(pageQuery, whereModel.arguments.toTypedArray())
		return PageImpl<WOrganization>(content, pageable, total)
	}

	private fun queryWOrganizations(sql: String, args: Array<out Any>): List<WOrganization> {
		return jdbc?.queryForList(sql, *args)?.map {
			WOrganization().apply {
				id = it["id"]?.toString()
				json = it["json"]?.toString()
				name = it["name"]?.toString()
			}
		} ?: listOf()
	}
}