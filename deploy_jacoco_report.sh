#!/bin/bash

mvn clean test jacoco:report

cf push vvps-project-test-report --buildpack staticfile_buildpack \
  --no-manifest --path target/site/jacoco \
  --instances 1 --memory 64M --disk 10M