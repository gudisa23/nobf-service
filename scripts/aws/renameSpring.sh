#!/usr/bin/env bash

set -e

ARTIFACT_ID=nobf-service
APP_HOME=/appfiles/${ARTIFACT_ID}

main() {
    chown -R spring:spring ${APP_HOME}
}

main
