#!/usr/bin/env bash

set -e

ARTIFACT_ID=nobf-service
APP_HOME=/appfiles/${ARTIFACT_ID}

main() {
    chmod 500 ${APP_HOME}/${ARTIFACT_ID}.jar
    chkconfig ${ARTIFACT_ID} on
    nohup service awslogs start
    nohup service ${ARTIFACT_ID} start
}

main
