#!/bin/sh

docker restart blue-squirrel-mariadb 2> /dev/null
if [ $? -eq 0 ]; then
    echo "Database restarted!"
else
    docker run --name blue-squirrel-mariadb -e MYSQL_ROOT_PASSWORD=test -d -p 3306:3306 mariadb

    until [ $(mysql -h localhost -P 3306 --protocol=tcp -uroot -ptest -e"select 'CONNECTED' AS ''" 2> /dev/null | grep CONNECTED) ]
    do
        echo "Trying to connect to DB..."
        sleep 5
    done

    echo "Database started!"
    echo "Generating 'bluesquirrel DB and user..."
    ./create-db.sh
fi
