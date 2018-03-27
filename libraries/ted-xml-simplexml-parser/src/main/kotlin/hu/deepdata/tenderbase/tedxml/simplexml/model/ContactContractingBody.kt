/*
 *    Copyright 2018 DeepData Ltd.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package hu.deepdata.tenderbase.tedxml.simplexml.model

import org.simpleframework.xml.*

/**
 * @author Zsolt Jurányi
 */
data class ContactContractingBody(
		@field:Element(name = "ADDRESS", required = false)
		var address: String? = null,

		@field:Element(name = "CONTACT_POINT", required = false)
		var contactPoint: String? = null,

		@field:Attribute(name = "VALUE")
		@field:Path("COUNTRY")
		var country: String? = null,

		@field:Element(name = "E_MAIL", required = false)
		var email: String? = null,

		@field:Element(name = "FAX", required = false)
		var fax: String? = null,

		@field:Element(name = "NATIONALID", required = false)
		var nationalId: String? = null,

		@field:Element(name = "NUTS")
		var nuts: TextAndCode? = null,

		@field:Element(name = "OFFICIALNAME")
		var officialName: String? = null,

		@field:Element(name = "PHONE", required = false)
		var phone: String? = null,

		@field:Element(name = "POSTAL_CODE", required = false)
		var postalCode: String? = null,

		@field:Element(name = "TOWN", required = false)
		var town: String? = null,

		@field:Element(name = "URL", required = false)
		var url: String? = null,

		@field:Element(name = "URL_BUYER", required = false)
		var urlBuyer: String? = null,

		@field:Element(name = "URL_GENERAL", required = false)
		var urlGeneral: String? = null
)