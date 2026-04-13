# Java Project

This is a basic Java project using Maven.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Project

Run the following command to compile the project:

```
mvn clean compile
```

## Running the Application

To run the main class:

```
mvn exec:java -Dexec.mainClass="com.example.HelloWorld"
```

## Testing

To run tests (if any):

```
mvn test
```

## Troubleshooting

- Ensure Java and Maven are installed and in your PATH.
- If you encounter compilation errors, check that the Java version matches the one specified in pom.xml.