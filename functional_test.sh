#!/bin/bash

xlsx_file="${1}"
expected_average="${2}"
expected_standard_deviation="${3}"
expected_variance="${4}"

if [ ! -f "${xlsx_file}" ]; then
    echo "${xlsx_file} does not exist."
fi

# Compile executable jar with dependencies and run unit tests
mvn clean install

if [[ $? -ne 0 ]] ; then
  echo "Maven compilation failed!"
  exit 1
fi

# gets version of maven project
project_version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# actually executes the program and streams the result in temp file
java -jar target/vvps-project-${project_version}-jar-with-dependencies.jar "${xlsx_file}" | tee results.tmp

cat results.tmp | grep "Average" | grep "${expected_average}"
if [[ $? -ne 0 ]] ; then
  echo "Average value differs from expected!";
  rm results.tmp
  exit 1
fi

cat results.tmp | grep "Variance" | grep "${expected_variance}"
if [[ $? -ne 0 ]] ; then
  echo "Variance value differs from expected!";
  rm results.tmp
  exit 1
fi

cat results.tmp | grep "Standard deviation" | grep "${expected_standard_deviation}"
if [[ $? -ne 0 ]] ; then
  echo "Standard deviation value differs from expected!";
  rm results.tmp
  exit 1
fi

rm results.tmp
