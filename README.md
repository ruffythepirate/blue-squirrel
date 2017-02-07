[![Coverage Status](https://coveralls.io/repos/github/ruffythepirate/blue-squirrel/badge.svg?branch=master)](https://coveralls.io/github/ruffythepirate/blue-squirrel?branch=master)

# Introduction

This is a test project for me where I try to learn some more Scala, while at the same time developing something that can be used. It is basically a blog. At a later point it might be seperated into mini services.

# Setup

## Backend

To run the application you need to install a mysql database.

1. If you are using mac, run `brew install mysql`
2. Follow the brew instructions saying that you should run `mysql.server start`
3. This should start the mysql server.
4. You now need to connect using your client: use /usr/local/mysql/bin/mysql -uroot  for a newly installed mysql instance.
5. When connected, run the command `CREATE DATABASE bluesquirrel;`
6. We now need to create the account for this database: `CREATE USER blueadmini;`
7. Run `GRANT ALL ON bluesquirrel.* TO blueadmin;` to give access to the database for your user.

You then need to create a private application.conf file at `conf/private/application.local.conf` that contains the following properties
* \# db.default.driver=com.mysql.jdbc.Driver
* \# db.default.url="jdbc:mysql://localhost/bluesquirrel"
* \# db.default.username=blueadmin
* \# db.default.password="the strong password"

where username and password are equal to what you have configured for your database.


# Building Frontend

This project stores it's frontend javascript files in the ui folder. It then uses gulp to compile and test these resources and then moves them into the javascript folder for the play project.

Prerequisites to get this to work are:

1. You must have npm installed.
2. go to the ui folder
3. run `npm install`
4. run `gulp copy-lib`


#### Selenium
To run some of the unit tests, selenium is required. You are required to install the chromedriver for selenium. On a mac you can do this through 

    brew install chromedriver

It might be that your chromedriver isn't compatible with your chrome version. In that case you can manually find a chrome driver here: https://sites.google.com/a/chromium.org/chromedriver/.
When you are manually downloading a chromedriver you must ensure that it is available in the path.



