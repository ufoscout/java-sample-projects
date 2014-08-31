Skeleton for web-app based on spring-boot (spring4) packaged as war
===================================================================

The skeleton includes:
- spring4
- spring-boot-web-starter
- angular-js configured
- minify for js and css files

To start the application with spring-boot-maven-plugin
mvn spring-boot:run

To test the version with minified files use jetty-maven-plugin
mvn clean package jetty:run-war

and browse to localhost:8080/index-min.html