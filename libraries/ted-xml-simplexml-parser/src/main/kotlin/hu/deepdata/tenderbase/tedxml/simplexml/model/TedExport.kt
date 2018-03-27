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
@Root(name = "TED_EXPORT")
data class TedExport(

		var tenderId: String? = null, // will be filled by TedExportParser

		// TODO TECHNICAL_SECTION - only deletion date can be important
		// TODO LINKS_SECTION - TED website links x5, useless

		@field:Element(name = "CODED_DATA_SECTION")
		var codedDataSection: CodedDataSection = CodedDataSection(),

		@field:Attribute(name = "DOC_ID")
		var docId: String = "",

		@field:Attribute(name = "EDITION")
		var edition: String = "",

		@field:Element(name = "FORM_SECTION")
		var formSection: FormSection = FormSection(),

		@field:Element(name = "TRANSLATION_SECTION")
		var translationSection: TranslationSection = TranslationSection(),

		@field:Attribute(name = "VERSION", required = false)
		var version: String? = null
)