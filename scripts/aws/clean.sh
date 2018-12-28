#!/usr/bin/env bash

set -e

ARTIFACT_ID=nobf-service
APP_HOME=/appfiles/${ARTIFACT_ID}
DEPLOY_JAR=${APP_HOME}/${ARTIFACT_ID}.jar
DEPLOY_CONF=${APP_HOME}/${ARTIFACT_ID}.conf

main() {
    rm -rf ${DEPLOY_JAR}
    rm -rf ${DEPLOY_CONF}
}

main
