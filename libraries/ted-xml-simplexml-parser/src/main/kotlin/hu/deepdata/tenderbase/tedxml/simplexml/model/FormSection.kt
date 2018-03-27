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
data class FormSection(
		@field:ElementListUnion(
				ElementList(entry = "F01_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F02_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F03_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F04_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F05_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F06_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F07_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F08_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F09_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F10_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F11_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F12_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F13_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F14_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F15_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F16_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F17_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F18_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F19_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F20_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F21_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F22_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F23_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F24_2014", inline = true, required = false, type = Form::class),
				ElementList(entry = "F25_2014", inline = true, required = false, type = Form::class)
		)
		var forms: List<Form> = mutableListOf()
)