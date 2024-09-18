#!/bin/env sh

echo '@timestamp@'
echo '${timestamp}'
echo '@maven.build.timestamp@'
echo '${maven.build.timestamp}'
echo '@my.name@'
echo '${my.name}'

# - set the environment variables
# - call %cataline_home%/bin/start %1 %2 %3
# 