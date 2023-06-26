#!/bin/bash

java --version >/dev/null 2>&1
if [ $? -ne 0 ]; then
    exit 1
fi
nohup java -jar ./law-users/target/law-users-1.0-SNAPSHOT.jar > users_out.log 2>&1 &
nohup java -jar ./law-regulations/target/law-regulations-1.0-SNAPSHOT.jar > regulations_out.log 2>&1 &
