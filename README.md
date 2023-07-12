# Scholarship Management System

The scholarship management System, is a web application platform that provides the means to apply for scholarship as a WASSCE certificate holder and automate the scholarship processing process.

The web application scholarship management system written in Java Springboot and used thymeleaf for the front-end.

### About Springboot
Helps developers build applications with ease and with far less toil than other competing paradigms. Embedded web servers, auto-configuration, and “fat jars” help you get started quickly, and innovations like LiveReload in Spring DevTools mean developers can iterate faster than ever before. Alternate to the spring boot framework is the Quarkus framework.

The decision to go with Springboot was due to ease of use and preference. And Spring Boot applications are easy to deploy.

## Instalation 
* Clone project
```sh
$ https://github.com/Alfred-Kobby/Scholarship-Management-System.git
```
* Install MySQL 
```sh
sudo apt install mysql-server
```

* Create Database
```sh
CREATE DATABASE IF NOT EXISTS scholarship;
```

## Compilation/Starting Application
* Mac OS/Linux
```sh
./gradlew bootRun
```

* Windows
```sh
.\gradlew.bat bootRun
```

## Usage
* Enter the "http://localhost:8080/" in your browser and press enter
* Select the signIn or Register to enter your details and access the applicant dashboard
* See your application history and opt to apply for a new scholarship.
* Admin after logging in, gets to see all received scholarships and hit the process scholarship button to process it

## Architecture
![The Maze Flow Chart](https://imgur.com/a/Lf3ddj9)