# fruitMarketplace
A simple implementation for the "Blended fruit drinks marketplace" exercise.

For this implementation, I used Java (as requested).

- Some compromises were made, given the time frame for the resolution of the practical exercise
- I used JUnit to test the implementation
- I have provided a CSV file to pre-load the Inventory of the Marketplace

## FILE STRUCTURE
The project directory contains:

- src/main/java - Source code for the app
- src/main/resources - Contains a CSV file example, used to populate the main Inventory
- src/test/java - Source code for the unit tests

- pom.xml - Maven POM descriptor used to build an executable jar, and execute unit tests


## PREREQUISITES
I used Java version 8 to build and compile. You can download it from the Oracle website. After installing it, you should follow the instructions to make it accessible from your command line interface.

The following environment variables should be defined:

- JAVA_HOME whose value is Java installation directory
- PATH, adding the variable JAVA_HOME followed by subdirectory bin


## INSTRUCTIONS TO COMPILE
I used Apache Maven to automatize the procedure of building and compiling the source code, to execute the Unit Tests, and to generate the executable jar. This was tested with Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)

As it is commonplace, you should execute the Maven commands from a command line (or an IDE).  Follow the instructions for a simple Maven installation and you should be fine.

Add the following environment variables to compile from command line:

- MAVEN_HOME whose value is Maven installation directory
- PATH, adding the variable MAVEN_HOME followed by subdirectory bin
- Please refer to Maven installation guide.

After you download this source code, open a command line interface and do the following:

1. Enter to project directory
2. Execute the command:

mvn clean install

This should create a subdirectory target within the current directory, containing the file fruitdrnk-mivs-0.0.1-SNAPSHOT.jar. This file is the executable compiled jar


## TO RUN UNIT TESTS
Open a command line interface and do the following:

1. Enter to project directory
2. Execute the command:

mvn test

This should create a subdirectory surefire-reports in the current directory containing the JUnit reports


## INSTRUCTIONS TO RUN THE APP
Place the executable jar file fruitdrnk-mivs-0.0.1-SNAPSHOT.jar (found in directory target after compiling) and input text file containing the inventory, in the same directory.

A copy of the required CSV file is provided in directory src/resources within this project.

In command line:
1. Enter to the directory containing the executable jar file
2. Execute the command:

java -jar fruitdrnk-mivs-0.0.1-SNAPSHOT.jar .\Inventory.csv

If no Inventory file is found, the app will throw an Exception and will not work.

If the file is found in the directory, the program will execute.

##############################################################


Greetings

Marco Vaca
Quito, Ecuador
