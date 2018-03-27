## Requirements

TED-XML downloader is a simple Bash script which **calls WGET** to fetch contents of the [TED FTP server](ftp://ted.europa.eu/).

On Linux, `wget` may be part of the system, or you can easily install it from package, e.g. `apt-get install wget`. On Windows, you need to download [GNU WGET](https://www.gnu.org/software/wget/) and add it to the `PATH` environment variable.



## Directory structure

The TED FTP server provides XML in both daily and monthly packages. We are **using the daily packages**, this way updating our database on a daily basis is more effective. Structure of the FTP server:

```
daily-packages/
	1993/
	...
	2017/
		01/
		...
		12/
			YYYYMMDD_YYYYOJS.tar.gz
monthly-packages/
News/
Resources/
```

The script iterates through a range of years and downloads `daily-packages/YYYY` directories from the server. It only downloads new or modified files and the output directory will have the structure of TED's `daily-packages` directory, e.g.

```
/
	2015/
	2016/
	2017/
		01/
		...
		12/
			YYYYMMDD_YYYYOJS.tar.gz
```



## Configuration


The whole downloader script is the following:

```bash
#!/bin/bash

PREFIX="./daily-packages"
START=2016
END=`date +"%Y"` # current year
YEARS=`seq $START $END`

for y in $YEARS; do
	wget -N -nH -P $PREFIX -q -r --cut-dirs=1 --no-remove-listing \
		ftp://guest:guest@ted.europa.eu/daily-packages/$y
done
```

You can configure the script by modifying the values of the 4 variables:

* `PREFIX` is the target directory
* `START` is the earliest year you are interested in
* `END` is the last year you are interested in

Normally, you only need to set the `PREFIX` and `START` values once then you can schedule the script to update the directory regularly.