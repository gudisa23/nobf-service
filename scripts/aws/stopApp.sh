#!/usr/bin/env bash

set -e

ARTIFACT_ID=nobf-service
APP_HOME=/appfiles/${ARTIFACT_ID}
DEPLOY_JAR=${APP_HOME}/${ARTIFACT_ID}.jar

main() {
    checkInitd
    # if no jar then its not running!
    if [ -f ${DEPLOY_JAR}  ]; then
        echo "Jar Exists!"
        echo "Making jar 500!"
        chmod 500 ${DEPLOY_JAR}
        echo "Stopping service"
        service ${ARTIFACT_ID} stop
    fi
    service awslogs stop
}

checkInitd() {
    echo removing init.d
    rm -rf /etc/init.d/${ARTIFACT_ID}
    echo "adding init.d : ${DEPLOY_JAR} /etc/init.d/${ARTIFACT_ID}"
    ln -s ${DEPLOY_JAR} /etc/init.d/${ARTIFACT_ID}
}

main
