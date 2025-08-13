# 🧪 Software Testing Project — BankAccount & eBay UI Automation

## 📌 Overview
This project was built to **practice and apply software testing techniques** at two distinct levels:
1. **Unit Testing** with **JUnit 4** for the `BankAccount` class.
2. **User Interface (UI) Testing** with **Selenium WebDriver + TestNG** on the [eBay](https://www.ebay.com/) website.

---

## 🏦 BankAccount Class Features

- Attributes:
  `accountNumber`
  `accountHolder`
  `balance`
- Main Methods:
  `deposit(amount)`
  `withdraw(amount)`
  `transfer(recipient, amount)`

---

## 🧪 JUnit 4 Testing (BankAccountTest)

Applied Techniques
- Combine multiple test classes into one Test Suite.
- Data-driven tests with multiple datasets (Parameterized).
- CSV Logging.

## 🌐 UI Testing with Selenium + TestNG

Applied Techniques
- Selenium WebDriver (ChromeDriver) for browser automation.
- TestNG Annotations (`@BeforeClass`, `@BeforeMethod`, `@Test`, etc.).
- CSV Logging.

---

## ⚙️ Maven Configuration

- **Java 17**
- **Dependencies:**
  - `selenium-java: 4.25.0`
  - `junit: 4.13.2`
  - `testng: 7.7.0`
- **Surefire Plugin:**
  - Runs both JUnit (*Test.java) and TestNG (*NGTest.java) tests.

## 🚀 Running the Project

Run all tests

```

mvn clean test


```

Run only JUnit tests

```

mvn -Dtest=BankAccountTest test

```

Run only Selenium TestNG tests

```

mvn -Dtest=seleniumNGTest test

```
