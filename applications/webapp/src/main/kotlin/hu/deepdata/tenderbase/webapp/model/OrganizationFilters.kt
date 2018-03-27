package hu.deepdata.tenderbase.webapp.model

/**
 * @author Zsolt Jurányi
 */
data class OrganizationFilters(
		var name: String = ""
) {
	fun isFiltering() = !toString().isBlank()

	override fun toString() = mutableListOf<String>().apply {
		if (!name.isBlank()) add("name=$name")
	}.joinToString("&")
}