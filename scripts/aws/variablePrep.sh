#!/usr/bin/env bash
#
# this script replaces certain ~~X~~ fields with dynamic values
#
set -e

ARTIFACT_ID=nobf-service
APP_HOME=/appfiles/${ARTIFACT_ID}
DEPLOY_CONF=${APP_HOME}/${ARTIFACT_ID}.conf
INSTANCE_ID=
ENVIRONMENT=
DB_HOST=
DB_USER=
DB_PW=
LOGS_CONF_FILE="/etc/awslogs/awslogs.conf"

main() {
    getInstanceId
    getEnvironment
    getDbHost
    getDbUser
    getDbPw
    replaceAll ${LOGS_CONF_FILE}
    replaceAll ${DEPLOY_CONF}
}

getInstanceId() {
    INSTANCE_ID=$(/usr/local/bin/ec2-metadata -i)
    if [ -z ${INSTANCE_ID} ]; then
        error "Failed to get the instance id"
    fi
    echo "INSTANCE_ID -> ${INSTANCE_ID}"
    # parse it! format "instance-id: i-016da244a6933f407"
    INSTANCE_ID=$(echo "${INSTANCE_ID}" | cut -d ' ' -f 2)
    if [ -z ${INSTANCE_ID} ]; then
        error "Failed to parse the instance id"
    fi
    echo "INSTANCE_ID -> ${INSTANCE_ID}"
}

getEnvironment() {
    P_IMPORT="import sys, json;"
    ENVIRONMENT=$(aws ec2 describe-tags --filters "Name=resource-id,Values=${INSTANCE_ID}" | grep -2 environment | grep Value | tr -d ' ' | cut -f2 -d: | tr -d '"' | tr -d ',')
    echo "ENVIRONMENT -> ${ENVIRONMENT}"
    if [ -z ${ENVIRONMENT} ]; then
        error "Failed to get the environment!"
    fi
}

getDbHost() {
     DB_HOST=$(aws ssm get-parameters --names collaborate-service.db.host --with-decryption --region us-east-2 --output text)
     DB_HOST=$(echo ${DB_HOST} | cut -d ' ' -f4)
     notBlank "DB_HOST" ${DB_HOST}
}

getDbUser() {
     DB_USER=$(aws ssm get-parameters --names collaborate-service.db.user --with-decryption --region us-east-2 --output text)
     DB_USER=$(echo ${DB_USER} | cut -d ' ' -f4)
     notBlank "DB_USER" ${DB_USER}
}

getDbPw() {
     DB_PW=$(aws ssm get-parameters --names collaborate-service.db.pw --with-decryption --region us-east-2 --output text)
     DB_PW=$(echo ${DB_PW} | cut -d ' ' -f4)
     notBlank "DB_PW" ${DB_PW}
}

replaceAll() {
    FILE=$1
    findReplace environment ${ENVIRONMENT} ${FILE}
    findReplace ARTIFACT_ID ${ARTIFACT_ID} ${FILE}
    findReplace DB_HOST ${DB_HOST} ${FILE}
    findReplace DB_USER ${DB_USER} ${FILE}
    findReplace DB_PW ${DB_PW} ${FILE}
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
