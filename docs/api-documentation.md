The API provides you an easy way to get structured data from this website. When you're browsing the website, you can find buttons with code icon (<i class="fa fa-code"></i>), they provide the API links to the related content. API URLs are under `/api`.

The API returns data in JSON format compatible with the OCDS standard. You should always check the HTTP status code of the API response:

* `200` - everything is OK
* `404` - no content found for the given criteria
* `500` - internal error occurred

In the case of internal error, please contact us and include the API URL and the received response in your email.



## /api/organization/{id}

Returns the organization with the given ID. The database stores the latest found version of the organizations.

Parameter | Description
----------|------------
id        | Path variable; organization identifier



## /api/organization/{id}/releases/?role={role}

Returns the releases where the given organization ID appears in the given role.

Parameter | Description
----------|------------
id        | Path variable; organization identifier
role      | OCDS party role
page      | 0-based page number, default: `0`
size      | Number of items per page, default: `10`

## /api/organizations/

Returns the organizations matching the filter criteria.

Parameter | Description
----------|------------
name      | Name filter, treated as one expression, whitespaces are replaced to wildcards (e.g. `something to search` -> `%something%to%search%`)
page      | 0-based page number, default: `0`
size      | Number of items per page, default: `10`



## /api/process/{ocid}

Returns a list of releases having the given OCID.

Parameter | Description
----------|------------
id        | Path variable; OCID



## /api/release/{id}

Returns the release with the given ID.

Parameter | Description
----------|------------
id        | Path variable; release identifier



## /api/releases/

Returns the releases matching the filter criteria.

Parameter    | Description
-------------|------------
buyerName    | Buyer name filter, treated as one expression, whitespaces are replaced to wildcards (e.g. `something to search` -> `%something%to%search%`)
date         | Date range filter for publish date, format: `YYYY-MM-DD - YYYY-MM-DD`
maxValue     | Maximum tender/award value in EUR
minValue     | Minimum tender/award value in EUR
supplierName | Supplier name filter, treated as one expression, whitespaces are replaced to wildcards (e.g. `something to search` -> `%something%to%search%`)
tag          | OCDS release tag
title        | Tender title filter, treated as one expression, whitespaces are replaced to wildcards (e.g. `something to search` -> `%something%to%search%`)
page         | 0-based page number, default: `0`
size         | Number of items per page, default: `10`