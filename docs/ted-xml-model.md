## Why TED-XML?

[TED](http://ted.europa.eu/) serves tender information in two ways. Notices can be viewed (and downloaded) **in HTML** on their website, and there's their public **[FTP server](ftp://guest:guest@ted.europa.eu/)** where **notice XMLs** can be downloaded in daily/monthly packages. Both approach have advantages and disadvantages:

Feature                      | HTML                         |   | XML
-----------------------------|------------------------------|---|------------------------------
Availability                 | Latest 5 years only          | &lt; | **Full archive of 20+ years**
Crawling                     | Not elegant, harmful         | &lt; | **Packaged for bulk download**
File size<sup>1</sup>        | ~ 30 KB - 1.3 MB             | &lt; | **~ 7 KB - 200 KB**
Parsing difficulty           | Ugly, non-semantic HTML      | &lt; | **Language independent XML**
Notice completeness          | Not 100% of data<sup>2</sup> | &lt; | **Full notice**
Reference to related notices | **All notice of tender**     | &gt; | Only direct parent notice

<small><sup>1</sup> The smallest and largest XML file in the `20171011_2017195.tgz` daily package and their corresponding HTML size on the TED website.</small><br>
<small><sup>2</sup> E.g. award criteria (AC field) is missing from the "Data" tab for newer notices, but can be found in XMLs.</small>

While [Red Flags](http://www.redflags.eu/) works well with HTML, it seems that in the long run, using XMLs is a better choice.



## TED-XML schemas

There are two XML schemas being currently used by TED, [2.0.8](ftp://guest:guest@ted.europa.eu/Resources/XML%20schema%202.0.8/TED_Schema_R208S02.zip) and [2.0.9](ftp://guest:guest@ted.europa.eu/Resources/XML%20schema%202.0.9/TED_Schema_R209S01.zip). 2.0.9 is the latter one and appeared at some time in 2016, while R2.0.8 can still be found in 2017.

Currently, we **only support schema version 2.0.9** and will extend the parser to other versions later.



## Problems with TED-XML

Though TED-XMLs seem to contain more information than the HTMLs, they are missing a key feature. While TED website has a "Document family" tab for all notices which references all notices of the tender, XMLs **only** contain reference for the **direct parent notice** of the current notice.

The direct parent notice is **not always the previous** notice. Example tender:

Notice ID                                                                       | Document type          | Referenced notice
--------------------------------------------------------------------------------|------------------------|------------------
[409527-2016](http://ted.europa.eu/udl?uri=TED:NOTICE:409527-2016:TEXT:EN:HTML) | contract notice        | -
[418746-2016](http://ted.europa.eu/udl?uri=TED:NOTICE:418746-2016:TEXT:EN:HTML) | additional information | 409527-2016
[399656-2017](http://ted.europa.eu/udl?uri=TED:NOTICE:399656-2017:TEXT:EN:HTML) | contract award notice  | 409527-2016

The direct parent notice is **not always the first notice** of the tender. Example tender:

Notice ID                                                                       | Document type          | Referenced notice
--------------------------------------------------------------------------------|------------------------|------------------
[346129-2017](http://ted.europa.eu/udl?uri=TED:NOTICE:346129-2017:TEXT:EN:HTML) | contract notice        | -
[353282-2017](http://ted.europa.eu/udl?uri=TED:NOTICE:353282-2017:TEXT:EN:HTML) | additional information | 346129-2017
[357352-2017](http://ted.europa.eu/udl?uri=TED:NOTICE:357352-2017:TEXT:EN:HTML) | additional information | 346129-2017
[392136-2017](http://ted.europa.eu/udl?uri=TED:NOTICE:392136-2017:TEXT:EN:HTML) | additional information | 346129-2017
[401616-2017](http://ted.europa.eu/udl?uri=TED:NOTICE:401616-2017:TEXT:EN:HTML) | additional information | 346129-2017
[424116-2017](http://ted.europa.eu/udl?uri=TED:NOTICE:424116-2017:TEXT:EN:HTML) | additional information | 346129-2017
[458065-2017](http://ted.europa.eu/udl?uri=TED:NOTICE:458065-2017:TEXT:EN:HTML) | contract award notice  | 424116-2017

As we see, we **cannot determine the tender ID** (first notice ID of the tender) at the time of processing one notice. Though we **can recognize first notices** of tenders: they don't reference any notice.

### Solution

* We have to use daily packages from TED, and we have to import notices in the order of their publication date, from oldest to newest.
* If the current notice doesn't reference any notice, we can save a tender ID. Otherwise, we can fetch the tender ID of the referenced notice from our database.
* If we imported the referenced notice (or its referenced notices transitively) before, we will surely get a tender ID for the current notice, since we flush data into the database after each daily package.
* If we don't have the referenced notice in the database, we can drop the current notice to hide incomplete tenders from the website.



## XML parsing method

The first tool that came to our mind was [JAXB](http://www.oracle.com/technetwork/articles/javase/index-140168.html), because it seemed that it could reduce the work time on XML parsing literally to minutes.

* It can **fill a POJO model** with extracted data.
* There's an official tool (`xjc`, bundled with JDK) which can **generate annotated POJO model** for JAXB from existing XML schema definition. The TED-XML schema is available on TED's FTP server.
* The JAXB parsing itself is just 2 lines of code.

But as we tried JAXB, it turned out that it's not the right tool for this job.

* The `xjc` tool can only generate the POJO for the 2.0.9 schema and **fails for 2.0.8**.
* In dozens of generated files, the generated namespace declaration in `@XmlSchema` and `@XmlElementRef` annotations were incorrect, causing JAXB to throw exception unless we **modify the namespace by hand.**
* In dozens of generated files, possible XML elements were represented by only a **generic list** of `JAXBElement` objects instead of separate fields.
* `xjc` generated 25 `F##_2014` classes, and all of them have more than one field having additional **numbered types**, like `BodyF##`. Since they have similar content, we would want to use them like `Form_2014` or `Body`.
* [XPath](https://www.w3.org/TR/1999/REC-xpath-19991116/) could provide a workaround for some of these problems, but we couldn't make [MOXy](http://www.eclipse.org/eclipselink/documentation/2.4/moxy/advanced_concepts005.htm)'s `@XmlPath` annotation work.

These things led us to **use [Simple XML](http://simple.sourceforge.net/) instead.**

* It also works by filling an annotated POJO model, though there's no tool for generating one.
* It can handle XPath expressions, though not the whole XPath specification.
* It **doesn't care about XML namespaces** at all.
* Handling of `F##_2014` XML elements can be achieved in a **much more elegant way** than in JAXB.
* Extending the model to other TED-XML schema versions seems **much more easier.**
