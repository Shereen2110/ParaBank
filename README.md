# ParaBank Automation Testing Project

## 📌 Project Overview
This project is an automated testing framework developed to test the core functionalities of the ParaBank web application.
The main goal of this project is to ensure the stability, functionality, and reliability of the application through automated test scenarios.

## 🎯 Project Objectives
- Validate critical user flows in the ParaBank application
- Detect functional issues early
- Reduce manual testing effort
- Generate clear and detailed test reports

## 🛠 Technologies & Tools Used
- Java
- Selenium WebDriver
- TestNG
- Maven
- Allure Report
- IntelliJ IDEA

## ✅ Test Scenarios Covered
- User Login (Positive & Negative Scenarios)
- Account Overview Verification
- Fund Transfer Between Accounts
- Transaction Confirmation Validation
- Error Message Validation
- Data-Driven Testing for multiple inputs

## ▶ How to Run the Project
1. Clone the repository to your local machine.
2. Open the project using IntelliJ IDEA.
3. Make sure Java and Maven are installed and configured.
4. Run the following command to execute the tests:
mvn test -Dgroups="invalidScenario" -Dbrowser="chroume" clean test
mvn test -Dgroups="validScenario" -Dbrowser="chroume" clean test
5. Generate and view the Allure report using:
 allure generate --clean
 allure open allure-report
## 👩‍💻 Author
**Sherin Ibrahim**
