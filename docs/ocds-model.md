Our project aims to store and make the information available in the format defined by the [Open Contracting Data Standard](http://standard.open-contracting.org).

We generated the POJO classes from the [OCDS release schema v1.1.1](http://standard.open-contracting.org/schema/1__1__1/release-schema.json). We used the awesome [jsonschema2pojo](https://github.com/joelittlejohn/jsonschema2pojo/releases) tool to generate the Java code:

```bash
jsonschema2pojo \
	--source release-schema.json \
	--target src/main/java \
	-a JACKSON2 \
	-il NON_EMPTY \
	-p hu.deepdata.tenderbase.ocds.jpa \
	-c3
```

After the generation, we renamed the following classes to match OCDS names:

Generated class name | Matching OCDS name
---------------------|-----------------------
`Party`              | `Organization`
`Buyer`              | `OrganizationReference`
`TenderPeriod`       | `Period`
