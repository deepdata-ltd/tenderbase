#!/bin/bash
#
#    Copyright 2018 DeepData Ltd.
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
#

PREFIX="./daily-packages"
START=2016
END=`date +"%Y"` # current year
YEARS=`seq $START $END`

for y in $YEARS; do
    wget -N -nH -P $PREFIX -q -r --cut-dirs=1 --no-remove-listing ftp://guest:guest@ted.europa.eu/daily-packages/$y
done