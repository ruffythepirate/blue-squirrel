#!/bin/sh

docker stop $(docker ps -qfname=blue-squirrel-mariadb) 2> /dev/null
docker container rm $(docker container ls -afname=blue-squirrel-mariadb -q) 2> /dev/null
