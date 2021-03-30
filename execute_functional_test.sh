#!/bin/bash
script_dir="$(dirname -- "$(realpath -- "${BASH_SOURCE[0]}")")"

source "${script_dir}/functional_test.sh" "functional-test-example.xlsx" "0.01082699" "0.007709719"

if [[ $? -ne 0 ]] ; then
  echo "Functional test failed!"
  exit 1
fi

echo "Functional test is successful!"