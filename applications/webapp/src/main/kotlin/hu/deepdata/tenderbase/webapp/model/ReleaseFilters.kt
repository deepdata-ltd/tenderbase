package hu.deepdata.tenderbase.webapp.model

/**
 * @author Zsolt Jurányi
 */
data class ReleaseFilters(

		// form fields:
		var buyer: String = "",
		var country: String = "",
		var date: String = "",
		var maxValue: Long? = null,
		var minValue: Long? = null,
		var supplier: String = "",
		var tag: String = "",
		var title: String = "",

		// settings:
		var maxDate: String = "",
		var minDate: String = ""

) {
	fun isFiltering() = !toString().isBlank()

	override fun toString() = mutableListOf<String>().apply {
		if (!buyer.isBlank()) add("buyer=$buyer")
		if (!country.isBlank()) add("country=$country")
		if (!date.isBlank()) add("date=$date")
		if ((maxValue ?: 0) > 0) add("maxValue=$maxValue")
		if ((minValue ?: 0) > 0) add("minValue=$minValue")
		if (!supplier.isBlank()) add("supplier=$supplier")
		if (!tag.isBlank()) add("tag=$tag")
		if (!title.isBlank()) add("title=$title")
	}.joinToString("&")
}