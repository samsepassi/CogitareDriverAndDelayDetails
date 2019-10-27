# CogitareDriverAndDelayDetails

# Build and Run
1. mvn clean package
2. cd target or copy the CogitareDriverAndDelayDetails-1.0-SNAPSHOT-jar-with-dependencies.jar to your desired directory
3. java -jar CogitareDriverAndDelayDetails-1.0-SNAPSHOT-jar-with-dependencies.jar

# Test

mvn test

# Persistence

* The following technologies are used for the ORM:
1. Hibernate 5
2. MySql 8

# Resources

* The following resource are located in the `\src\main\resources` folder:
1. `Sql.sql` : Containing the scripts to build data base tables (In this project DDL is generated automatically by ORM)
2. `DriverAndDelayDetails.txt` : Data is fetched from this file to be processed by the application.
