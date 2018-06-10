#!/bin/sh

mysql -h localhost -P 3306 --protocol=tcp -uroot -ptest -e "CREATE DATABASE bluesquirrel;"
mysql -h localhost -P 3306 --protocol=tcp -uroot -ptest -e "CREATE USER blueadmin;"
mysql -h localhost -P 3306 --protocol=tcp -uroot -ptest -e "SET PASSWORD FOR 'blueadmin' = PASSWORD('test');"
mysql -h localhost -P 3306 --protocol=tcp -uroot -ptest -e "GRANT ALL ON bluesquirrel.* TO blueadmin;"
