#!/bin/bash

java --version >/dev/null 2>&1
if [ $? -ne 0 ]; then
    exit 1
fi
java -jar law-users-1.0-SNAPSHOT.jar
java -jar law-regulations-1.0-SNAPSHOT.jar
