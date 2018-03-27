TenderBase grabs fresh notices every day from [TED](http://ted.europa.eu/), converts them into [OCDS](http://standard.open-contracting.org/) releases and stores it in its database. Note that only those notices are being processed which are using the newer TED-XML (2.0.9) schema.

The [API](api-documentation.md) provides an easy way to access release and procurement process information in JSON format, using OCDS. You can find JSON API links on list and detail pages.



## Navigation

Every page has a **navigation bar at the top.** On the left side, you can always go back to the index, and on the right side there are the main features of the side: release list, organization list and resources.

On mobile, the right side links disappear, and a "hamburger menu" icon (<i class="fa fa-bars"></i>) can show/hide them in a drop down menu.



## Releases

You can reach the release list page from the navigation bar or from the homepage using the button below the latest releases. The list displays the most important information about each release:

* publish date
* release tag
* tender title
* tender/award value in EUR
* buyer name

EUR values are there to give a unified view about each tender's value. As **we calculate them based on a fixed currency rate from the past,** real value may differ now. You can find the real value in the original currency if you navigate to the details page of a release by clicking on its title.

You can filter the list if you click on the gray Filters bar above it. This panel is automatically opened if any filter is active. You can filter for the same fields listed above and also for the supplier name.

Text filters handle the specified value as **one expression**, and every whitespace in it as a wildcard. For example, if you search for `eeny meeny miny moe` in the title, only those titles will appear which contain **all** these words and in this exact **order**, but not necessarily each after other, e.g. `v eeny w meeny x miny y moe z` title will also come up.

Filter for published date accepts the format of `YYYY-MM-DD - YYYY-MM-DD`, though you can use the date range picker window to select the dates just by clicking on them. It has some predefined ranges, but you can set a custom range easily.

You can turn the pages using the buttons below the release list, on the left. On the right, you can find the API link for the current page.

If you click on a title, you can navigate to the details page of that release.

The release details page displays the available fields of the release divided into sections which follow the OCDS terminology:

* Release: simple fields of the release object, e.g. identifier, buyer and language
* Process: displays the process identifier (OCID) and all releases from the database that belong to the same tender
* Tender: fields of the tender, e.g. title, category and periods
* Award: each award produces an Award section with award information, e.g. value and the supplier
* Parties: all organizations involved in the release: buyer, tenderers, suppliers, etc.

If a section above *Parties* references an organization, that link will make the browser jump down to the detailed organization block in the *Parties* section. These links are noted by a down arrow icon (<i class="fa fa-level-down"></i>). In detail blocks, there are buttons for the organization page and for the API call which returns organization data.



## Organizations

Organizations list page can be accessed from the navigation bar. The list displays only the organization names as links which lead to the detail pages.

You can filter the list if you click on the gray Filters bar above it. This panel is automatically opened if any filter is active. You can filter organizations by name, it works as the same as the text filters described above.

You can turn the pages using the buttons below the organization list, on the left. On the right, you can find the API link for the current page.

Organization detail pages show the latest found address of the organization. At the end of the field list, there is a button for the API call which returns the address in JSON.

Below, all releases are listed where the organization takes part. The releases are grouped by the organization roles (buyer, supplier, etc.) and these roles are displayed as tabs with the release count. You can find the same pager and API buttons at the bottom, as on the release list pages we described above.