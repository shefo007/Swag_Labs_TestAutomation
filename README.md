# Swag_Labs_TestAutomation

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.oracle.com/java/)  
[![Maven](https://img.shields.io/badge/Maven-3.8.8-007dba?logo=apache-maven)](https://maven.apache.org/)  
[![TestNG](https://img.shields.io/badge/TestNG-7.5.0-green)](https://testng.org/)  
[![Build Status](https://github.com/shefo007/Swag_Labs_TestAutomation/actions/workflows/maven.yml/badge.svg)](https://github.com/shefo007/Swag_Labs_TestAutomation/actions)  

## 🧾 Table of Contents

1. [Project Overview](#project-overview)  
2. [Features](#features)  
3. [Tech Stack](#tech-stack)  
4. [Prerequisites](#prerequisites)  
5. [Setup & Installation](#setup--installation)  
6. [Usage / Running Tests](#usage--running-tests)  
7. [Folder Structure](#folder-structure)  
8. [Contributing](#contributing)  
9. [License](#license)  

---

## Project Overview

This repository is an automation framework for **SwagLabs** (the SauceDemo / Swag Labs web application).  
It uses Java + Maven + TestNG to structure and run end-to-end UI tests.

The goal is to have a scalable, maintainable framework where new tests can be added easily, reports are generated, and execution can be integrated into CI.

---

## Features

- Page Object Model (POM) design  
- Data-driven / parameterized tests  
- Thread-safe execution using `ThreadLocal` (parallel runs)  
- Test reports and logs  
- Easy to extend with additional tests or pages  

---

## Tech Stack

| Layer | Technology / Tool | Purpose |
|---|---|---|
| Language | Java | Core programming language |
| Build / Dependency | Maven | Managing dependencies, builds, test runs |
| Test Framework | TestNG | Structuring test suites, annotations, parallelism |
| Design Pattern | Page Object Model (POM) | Separating page interactions from test logic |
| Reporting & Logging | (future: e.g. Allure, ExtentReports) | Generating human-readable test reports |
| Version Control | Git / GitHub | Source code management |

---

## Prerequisites

Before you run the tests, make sure you have:

- Java JDK (version 11, 17, or whichever version you target)  
- Maven installed and configured (`mvn` accessible in terminal)  
- A modern web browser and matching WebDriver (ChromeDriver, GeckoDriver, etc.)  
- Internet connection (for accessing the application under test)  

---

## Setup & Installation

1. Clone the repo  
   ```bash
   git clone https://github.com/shefo007/Swag_Labs_TestAutomation.git
   cd Swag_Labs_TestAutomation
