# Meet in the Middle
Meet in the Middle is a web application that allows users to find places of interest
in the middle of two given locations.

## Spring Tutorial Used
Use [this](http://shengwangi.blogspot.com/2016/08/jersey-in-spring-boothello-world-example.html) as reference for further development

## Setup
In order for things to work, if running Spring application from IntelliJ, set the environment variable as the
one from the 'appenv.sh' file within IntelliJ by changing configurations. Then, just run the "MeetMiddleApplication.java" file.

If running from command line first run 
`source appenv.sh`
to set the environment variable and then run
`mvn spring-boot:run`
to run the application.

## Current things to be done
Everything is set up including [Google Places API client](https://github.com/windy1/google-places-api-java) and library to [calculate midpoint.](https://github.com/grumlimited/geocalc) Only thing left to do
is to fit the methods from Google Places with midpoint calculation and find list of places.