#!/bin/bash

VERSION=$(cat .version)

mvn versions:set \
	-DgenerateBackupPoms=false \
	-DnewVersion=$VERSION \
	-DoldVersion=* \
	-DgroupId=hu.deepdata.tenderbase \
	-DartifactId=tenderbase-build

sed  -i "s/\*\*Version .*\*\*/**Version $VERSION**/" docs/README.md