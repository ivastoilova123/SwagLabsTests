# 🧪 Running Java Test Cases

This project contains automated test cases written in Java using TestNG and Selenium. You can run the tests using Maven or using TestNG annotations.

---

## 🛠 Requirements

- Java 10 or higher
- Maven installed
- IDE -> IntelliJ
---

## ▶️ Run Tests with Maven

Make sure you are in the project root directory (where the `pom.xml` file is located), then run:

# For TestNG
mvn test

# For specific test class, TestNG
mvn -Dtest=ClassName test

## ▶️ Run Tests FROM IDE

- Open the project in your IDE.

- Navigate to the test file.

- Right-click the test method or class and choose Run.


## ▶️ Example Directory Structure
 
src
 ├── main
 │   └── java
 │       └── commonUsed
                  └── DriverSetUp
                             └── pages
                                       └── LoginPage              

 └── test
     └── java
         └── tests
                └── LoginPageTests
