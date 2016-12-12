# Introduction

This is a test project for me where I try to learn some more Scala, while at the same time developing something that can be used. It is basically a blog. At a later point it might be seperated into mini services.

# Building Frontend

This project stores it's frontend javascript files in the ui folder. It then uses gulp to compile and test these resources and then moves them into the javascript folder for the play project.

Prerequisites to get this to work are:

1. You must have npm installed.
2. You need to install a couple of npm modules:

        npm install -g yo gulp bower
        


#### Selenium
To run some of the unit tests, selenium is required. You are required to install the chromedriver for selenium. On a mac you can do this through 

    brew install chromedriver

It might be that your chromedriver isn't compatible with your chrome version. In that case you can manually find a chrome driver here: https://sites.google.com/a/chromium.org/chromedriver/.
When you are manually downloading a chromedriver you must ensure that it is available in the path.



