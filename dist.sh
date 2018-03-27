#!/bin/bash

source update-version.sh

mkdir -p dist
rm -rf dist/*
mvn -q clean package \
	&& mv applications/ted-xml-importer/target/tenderbase-ted-xml-importer-*.jar dist/tenderbase-ted-xml-importer.jar \
	&& mv applications/webapp/target/tenderbase-webapp-*.jar dist/tenderbase-webapp.jar \
	&& mvn clean -q \
	&& cp scripts/*.* dist/ \
	&& cp application-example.yml dist/
