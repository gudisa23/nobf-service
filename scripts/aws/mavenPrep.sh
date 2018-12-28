#!/usr/bin/env bash

set -e

NEXUS_PASSWORD=
SONAR_QUBE_KEY=
MAVEN_SETTINGS="/root/.m2/settings.xml"

main() {
    getMavenSettings
    getNexusPw
    getSonarQubeKey
    replaceAll ${MAVEN_SETTINGS}
}

getMavenSettings() {
    aws s3 cp s3://bcg-deploy/maven/settings.xml ${MAVEN_SETTINGS}
    mvn -version
}

getNexusPw() {
     NEXUS_PASSWORD=$(aws ssm get-parameters --names bcg.aws.nexus.password --with-decryption --region us-east-2 --output text)
     NEXUS_PASSWORD=$(echo ${NEXUS_PASSWORD} | cut -d ' ' -f4)
     notBlank "CONFIG_SERVER_PW" ${NEXUS_PASSWORD}
}

getSonarQubeKey() {
     SONAR_QUBE_KEY=$(aws ssm get-parameters --names bcg.aws.sonarqube.key --with-decryption --region us-east-2 --output text)
     SONAR_QUBE_KEY=$(echo ${SONAR_QUBE_KEY} | cut -d ' ' -f4)
     notBlank "SONAR_QUBE_KEY" ${SONAR_QUBE_KEY}
}

replaceAll() {
    FILE=$1
    findReplace nexus_password ${NEXUS_PASSWORD} ${FILE}
    findReplace sonar_qube_key ${SONAR_QUBE_KEY} ${FILE}
}

findReplace() {
    FIND=$1
    notBlank "FIND" ${FIND}
    REPLACE=$2
    notBlank "REPLACE" ${REPLACE}
    FILE=$3
    notBlank "FILE" ${FILE}
    echo "findReplace - FIND:${FIND} - FILE:${FILE}"
    sed -i -e "s/~~${FIND}~~/${REPLACE}/g" ${FILE}
}

notBlank() {
    FOO_NO_LEAD_SPACE="$(echo -e "${2}" | sed -e 's/^[[:space:]]*//')"
    if [ -z ${FOO_NO_LEAD_SPACE} ]; then
        error "Blank! $1"
    fi
}

error() {
    printf "-\n-\n- ERROR -\n$1\n-\n$0\n-\n- Error -"
    exit 1
}

main