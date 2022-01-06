#!/usr/bin/env bash

# A small script used to generate parsed JSON model from program source code.
# Assume Parsing server is running on 0.0.0.0:5000.

cd "$(dirname "$0")"
mkdir -p ./resources/model ./resources/input
for src_name in $(ls ./resources/source/); do
    dst_path=./resources/model/$src_name.json
    input_path=./resources/input/$src_name.in
    touch $dst_path $input_path
    jq -Rs '{"language": "c", "program": .}' < ./resources/source/$src_name \
        | http -b POST 0.0.0.0:5000/parser \
        | jq '.model' > $dst_path
done
