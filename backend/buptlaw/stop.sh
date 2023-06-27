#!/bin/bash

# kill java jar processes
pids="$(ps -ef | grep "java -jar" | grep -v grep | awk '{print $2}')"
if [ -n "$pids" ]; then
    echo "killing java jar processes: ${pids}"
    kill -9 ${pids}
fi