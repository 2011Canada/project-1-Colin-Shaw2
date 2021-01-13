
# Colin's Expense Reimbursement System

## Overview

An expense Reimbursement System that allows employees to create new reimbursement requests and allow finance managers to review and approve and deny them.  This project uses a tomcat cat server to run the back end and basic Js/Css/Html on the front end to send HTTP requests to the server.  All data is persisted in a postgreSQL database deployed to on AWS using the RDS service.

## Technologies Used

* Javascript ES6
* Java 8
* Tomcat 9
* CSS
* HTML5
* JDBC
* PostgreSQL 13.1


## Features

* Uses colours to quickly convey status of requests
* Employees can add new reimbursement requests
* Employees can view past requests and their status
* Finance managers can view all requests and approve or deny them
* Finance managers can filter requests by requests status

To-do list:
* Allow users to store images in requests

## Getting Started
   
* Download the code from git hub git clone https://github.com/2011Canada/project-1-Colin-Shaw2.git
* Setup a postgreSQL database using the ERSDataBaseDefaultsColin.sql script
* Set Env vars DB_PASSWORD, DB_USER, DB_URL
* Run the server
* Go to http://localhost:8080/p1colin/frontend/login.html in a webbrowser to access the application
* You should get a login screen that looks like this
* ![alt text](https://github.com/2011Canada/project-1-Colin-Shaw2/blob/main/Screenshots/loginScreen.png?raw=true)


## Usage
  - Login with username and password and you will be taken to the appropriate user interface
  - Depending on your credentials you will be able to view add or approve transactions
  - Employees should see a similar screen
![alt text](https://github.com/2011Canada/project-1-Colin-Shaw2/blob/main/Screenshots/employeeScreen.png?raw=true)
  - Finance managers should see a similar screen 
![alt text](https://github.com/2011Canada/project-1-Colin-Shaw2/blob/main/Screenshots/Finance%20Manager%20Screen.png?raw=true)
  

## License

This project uses the following license: [GNU GENERAL PUBLIC LICENSE](<https://www.gnu.org/licenses/gpl-3.0.en.html>).
