## Repository contents

You can see the following directories and files from the repository root:

Entry            | Content
-----------------|------------
`applications/` | Source code of **executable applications** as part of the [Maven](https://maven.apache.org/) project defined in `pom.xml`.
`docs/`         | Files of this documentation you are viewing. The text is written in [Markdown](https://daringfireball.net/projects/markdown/syntax), and the interactive web-based documentation can be viewed by any webserver or using [Docsify](https://docsify.js.org/).
`libraries/`    | Source code of **components** that are not executable, but can be referenced in other projects. Programs in `applications/` directory do reference them as dependencies. These libraries are also part of the Maven project defined in `pom.xml`.
`scripts/`      | Shell and other kind of scripts that are needed in the project.
`.version`       | Text file containing only the current version of the project. It's used to modify it by hand in one place and then propagate the change to the rest of the repository via shell scripts.
`dist.sh`        | Shell script to build the project and copy the executables and necessary files into `dist/` directory.
`pom.xml`        | Maven **multi-module project** definition. It can be used to build all components at once. This is used also as a parent project in the components, to define common settings.
`update-version.sh` | Shell script to modify the version of all Maven projects and in the documentation to the value in the `.version` file.



## Maven project

The root `pom.xml` defines a **[multi-module](https://maven.apache.org/pom.html#Aggregation) Maven project.** This means that other Maven projects listed in this XML in `modules` section can be handled at once using this POM. For example, if you call `mvn clean package` on this root POM, **all modules** will be cleaned and packaged. The following modules are listed in the root POM:

Module                               | Description                                  | Language
-------------------------------------|----------------------------------------------|---------
`libraries/ted-xml-simplexml-parser` | SimpleXML model and parser for TED-XMLs      | Kotlin (JVM)
`libraries/ocds-jackson-model`       | Jackson model for OCDS                       | Java
`libraries/webapp-jpa-model`         | JPA model for web application                | Kotlin (JVM)
`applications/ted-xml-importer`      | TED-XML importer application                 | Kotlin (JVM)
`applications/webapp`                | TenderBase web application (website and API) | Kotlin (JVM)

So the root POM references application and library projects as modules. On the other hand, those project reference the **root POM as their [parent project](https://maven.apache.org/pom.html#Inheritance)**. This way they inherit **common settings** defined in the root POM, such as dependency versions and repository URLs.

The root POM itself also has a parent project, which is `spring-boot-starter-parent`. This is needed because Spring defines a lot of dependency versions that are safe to be used in our applications.

The `groupId` is `hu.deepdata.tenderbase` for all modules and the root POM. `artifactId` is always the directory name with `tenderbase-` prefix, e.g. `tenderbase-webapp`. The root POM's identifier is `tenderbase-build`.

If you need to reference one of these modules in your project, call `mvn clean install` first in the project root directory. Then you can add them to your POM:

```xml
<dependency>
	<groupId>hu.deepdata.tenderbase</groupId>
	<artifactId>tenderbase-MODULE</artifactId>
	<version>VERSION</version>
</dependency>
```



## Programming languages

The [OCDS model](ocds-model.md) contains POJO definitions generated using 3rd-party tools, so it's written in **Java**.

The rest of the project (applications, models, parsers) is written by us, in [Kotlin](https://kotlinlang.org/). Kotlin has many advantages over Java and speeds up development, while it's fully interoperable and **runs on the JVM.**

All modules compile to Java bytecode.



## Versioning

The multi-module project and all of it's components have one **common version number.** As you can see above, the version number is organized into one external file and a shell script helps us to modify it in every place where it's needed.

The versioning model follows [Semantic Versioning 2.0.0](http://semver.org/) ruleset.