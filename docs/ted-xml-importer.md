The TED-XML importer is a Java application written in Kotlin. It provides a way to import [TED-XML](ted-xml-model.md) files into the database by generating OCDS model and records for the web application.

It processes TED-XML files using a two-level **batch processing pattern.** On the first level, it processes each TAR file (daily package), one after another, drops unsupported and already processed ones, and manages meta records. Inside, it runs an XML processing chain, which works on the XMLs of the current TAR file. It parses XML files into a model in memory, generates OCDS and web application models and saves record into the database.



## Supported files

TAR and XML files are checked by their filenames, they have to match **TED's filename patterns.** A TAR filename should look like something like `20180105_2018003.tar.gz` and an XML should look similar as `002546_2018.xml`. The XML schema is also verified, only schema version `R2.0.9` is supported. The importer skips all non-supported files.

Furthermore, when the **optional country filter** is active, those XML files are also skipped where the buyer entity's country is not matching the filter.



## Meta records

If a file (TAR or XML) can **reach the end of the processing chain** (not skipped and there were no errors during processing), it **gets a file [meta record](database-model?id=file_meta).** It contains the filename, the timestamp when it's processed, the importer version and the error count (among other fields).

If an error occurs during an XML processing, the XML file won't get a meta record, and the TAR meta record's error counter increases.

If an error occurs during TAR processing (and it's not an XML error), the TAR meta record won't be stored.

Meta records are identified by the filename and the file content's MD5 checksum. Therefore **if a supported TAR file is modified, it will be surely processed in the next importer run.**



## Reprocessing

A TAR file is considered *processed* if it has a meta record with timestamp and 0 error count, and if all of its (supported) XML are *processed*. An XML file is *processed* if it has a meta record with timestamp and 0 error count.

**By default, the importer will skip files that are *processed*,** this way daily runs will only process the fresh files (and also the failed ones). However, this skipping can be tuned with `reprocessPolicy` configuration parameter. It has the following available modes:

* `NO`: no reprocessing, skips every *processed* file
* `ALL`: reprocesses every file, ignoring meta records
* `PATCH`: reprocesses *processed* files if the importer version in their meta record differs from the importers current version
* `MINOR`: reprocesses if version differs in major or minor parts
* `MAJOR`: reprocesses if version differs in the major part

**If you extend the scope in the country filter or by downloading earlier daily packages, it's recommended to use the `ALL` mode temporarily to reprocess everything.**



## OCDS identifier generation

The [identifier of an OCDS release](http://standard.open-contracting.org/latest/en/schema/identifiers/#) should made up from 3 things: the OCID prefix, the ID of the tendering process and the ID of the release. The OCID prefix [should be requested here](http://standard.open-contracting.org/latest/en/implementation/registration/).

For the tender ID, we chose to use the ID of the **first notice of that tender.** It is a bit [tricky to obtain](ted-xml-model?id=problems-with-ted-xml), but we can resolve the tender ID from daily-packages we processed earlier. Notice and tender IDs are also stored in the XML meta records. If the importer cannot resolve the tender ID for a notice, that notice will be dropped.

We **swap the number and year parts** of notice and tender IDs, to make them orderable. So now we have OCDS release IDs like:

```text
...
ocds-prefix:2017-399670:2017-399670
ocds-prefix:2017-400695:2017-400695
ocds-prefix:2017-400695:2018-003593
...
```

Award IDs are made up from the OCDS process ID (OCID prefix + tender ID) and the award index inside the contract award notice.

Organization IDs [should rely on global databases](http://standard.open-contracting.org/latest/en/schema/identifiers/#organization-ids) but we could not find any which contains all organizations, so we **generate SHA1 hash of the organizations name** and append it to the OCID prefix. We are doing some minimal, temporal cleaning on the name before we generate the hash, because there are cases when the same organization appears in multiple versions, e.g. one with a `.` at the end and one without. Cleaning means we remove whitespaces and most common symbols from the name, only for hashing.



## Configuration

There are 2 ways to pass configure the application: you can place an `application.yml` file beside the JAR file, or you can pass configuration parameters in the command line, e.g.

```
java -jar ted-xml-importer.jar --import.reprocessPolicy=ALL
```

The recommended way is to copy the `application-example.yml` file beside the JAR file as `application.yml` and customize it for your own needs.

```yaml
# This file uses YAML syntax (http://www.yaml.org/spec/1.2/spec.html).
# Do NOT use tab characters in this file.

# MySQL database connection parameters
db:
  # Database host IP or domain name, optionally with port number, e.g.
  # "domainOrIP" or "domainOrIP:1234"
  host: localhost

  # Name of the database
  name: tenderbase

  # User that has read/write permissions on the above database
  user: ...

  # User's password
  pass: ...

# TED-XML Importer settings
import:

  # Optional country filter. Should contain two-letter codes
  # separated with commas. When you extend the scope by
  # adding a country or disabling the filter, you should
  # use reprocessPolicy=ALL setting to process all data.
  #countryFilter: "HU,PL"

  # Directory where TED daily packages are located
  #dailyPackagesDir: "daily-packages"

  # Tune the log level of the application. You can use values
  # ERROR, WARN, INFO, DEBUG, TRACE or OFF. When you specify
  # DEBUG or TRACE, EasyBatch logging will be turned on too.
  #logLevel: "INFO"

  # OCID prefix to be used when generating OCDS releases
  # and records
  ocidPrefix: "ocds-ABC123"

  # When to reprocess XMLs based on previous process version.
  # NO: never, ALL: always, PATCH: on patch version increase,
  # MINOR: on minor version increase, MAJOR: on major version
  # increase.
  #reprocessPolicy: "NO"
```

## Implementation

The program is implemented using [Spring Boot](https://projects.spring.io/spring-boot/) framework to ease the pain of configuration and changing the database implementation if needed. A batch processing pattern is used to read, convert and write TED-XML data, and it's built on top of [Easy Batch](https://github.com/j-easy/easy-batch/wiki) framework.

There are 4 batch jobs in this importing mechanism:

* **TAR collector job:**
	* reads: TAR files from the input directory
	* record payload: `File`, then `TarRecord`
	* processing: drops unsupported and processed TAR files
	* writes: to queue
* **TAR processor job:**
	* reads: from queue
	* record payload: `TarRecord`
	* processing: calls XML collector and processor jobs
	* writes: TAR meta records into database
* **XML collector job:**
	* reads: XML files from the TAR file
	* record payload: `XmlRecord`
	* processing: drops unsupported and processed XML files
	* writes: to queue
* **XML processor job:**
	* reads: from queue
	* record payload: `XmlRecord`
	* processing: parses XML, drops by country, resolves tender ID, drops if fails, generates OCDS model, generates web application model
	* writes: web application records and XML meta record into database
