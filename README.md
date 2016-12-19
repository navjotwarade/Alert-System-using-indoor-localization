# Alert-System-using-indoor-localization

The project consists of the three components:
Android application – The application will have the user interface to send the alert to the Java Server
and gather fingerprints.
Java Server – to calculate the location using SVM machine learning algorithm
Node Server - to receive messages from the android application and Java Server and to render web
pages to web application

Technologies and libraries needed:
1) Nodejs v4.2.2
2) Npm v2.14.7 (modules required – system-sleep v1.0, open v0.0.5, mysql v0.9)
3) Java 1.7 or +
4) Gradle 2.2 (specific)
5) Android SDK API 23

Build System
To build the Java server, use the command:
gradle : server : assemble
To build the Android app.
gradle : android : assemble
To build the Node Server.
node app.js

Steps to build and run the complete system:
1) Download the redpin-master.zip file. Extract and navigate to that folder
2) Build the server by using command:
gradle : server : assemble
Navigate to the ‘libs’ folder in the server.
3) Locate the folder containing the server.jar file and run the command:
java -jar server.jar 8000
4) Now the Java server is running, navigate to redpin master and run command:
gradle : android : assemble
5) Navigate to ‘build’ folder in android and locate the. apk. This apk file can be downloaded to
android phone.
6) Navigate to ‘SmartCServer’ folder and run the command:
node app.js
7) Now the node server is running, you can run the app in mobile.
8) In mobile app connect to server using ‘Server Preferences’ tab on screen.
9) Once connected the application is ready for the use
