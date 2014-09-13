Skeleton for web-app based on spring-boot (spring4) packaged as war
===================================================================

The skeleton includes:
- spring4
- spring-boot-web-starter
- angularjs webjar
- angularjs unit tests with jasmine-maven-plugin
- minify for js and css files

Requirements:
- jdk 7
- phantomjs in the classpath
- maven 3

Jasmine is configured to use phantomjs instead of the default HtmlUnit. HtmlUnit, in fact, does not behave exactly like a real browser in many situations and does not work fine with angularjs.

The jasmine tests are executed during the maven test phase, they can be executed with the commands:
mvn jasmine:test -> execute all jasmine test
mvn jasmine:bdd -> run an embedded web container that permits to execute the tests in web browser

To start the application with spring-boot-maven-plugin
mvn spring-boot:run

To try the the packaged war with minified files use jetty-maven-plugin:
mvn clean package jetty:run-war

and browse to localhost:8080/index-min.html