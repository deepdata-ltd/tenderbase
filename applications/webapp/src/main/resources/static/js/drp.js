/*
	Copyright 2018 DeepData Ltd.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

const format = 'YYYY-MM-DD';

$('input.drp').each(function () {
	var $drp = $(this);
	var maxDate = $('#maxDate').val();
	var minDate = $('#minDate').val();
	$drp.daterangepicker({
		autoApply: true,
		autoUpdateInput: false,
		locale: {
			cancelLabel: "Clear",
			format: format
		},
		maxDate: maxDate,
		minDate: minDate,
		ranges: {
			'Today': [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'Last 7 Days': [moment().subtract(6, 'days'), moment()],
			'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			'This Month': [moment().startOf('month'), moment().endOf('month')],
			'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'This Year': [moment().startOf('year'), moment()],
			'Last Year': [moment().subtract(1, 'year').startOf('year'), moment().subtract(1, 'year').endOf('year')]
		},
		showDropdowns: true
	});
	$drp.on('apply.daterangepicker', function (ev, picker) {
		$(this).val(picker.startDate.format(format) + ' - ' + picker.endDate.format(format));
	});

	$drp.on('cancel.daterangepicker', function (ev, picker) {
		$(this).val('');
	});
});
