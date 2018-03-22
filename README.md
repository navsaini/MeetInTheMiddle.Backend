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

## Tasks completed
1) Everything is set up including [Google Places API client](https://github.com/windy1/google-places-api-java) and library to [calculate midpoint.](https://github.com/grumlimited/geocalc). 
2) Given two coordinates, a list of locations in the geographic midpoint can be found. 

## To Do
1) Make a custom JSON payload that has the calculated midpoint and a list of all of the places (with name, rating, hours, etc.)
2) Interate through the reviews and calculate an average rating to be used in the JSON payload sent to front end
3) Continue integration with front-end
