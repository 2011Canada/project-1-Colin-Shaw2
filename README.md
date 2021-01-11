# Colin's Expense Reimbursement System

## An expense Reimbursement System that allows employees to create new reimbursement requests and allow finance managers to review and approve and deny them.  This project uses a tomcat cat server to run the back end and basic Js/Css/Html on the front end to send HTTP requests to the server.  All data is persisted in a postgreSQL database deployed to on AWS using the RDS service.

### List of features implemented,
  - employees can add new reimbursement requests
  - employees can view past requests and their status
  - finance managers can view all requests and approve or deny them
  - finance managers can filter requests by requests status
  
### Technologies used
  - Javascript
  - css
  - html
  - Fetch API
  - Java
  - jdbc
  

### Set up
  - clone repo
  - create a database using the ERSDataBaseDefaultsColin.sql script
  - back end using appropriate system variable to connect to your database
  - go to http://localhost:8080/p1colin/frontend/login.html in your webbrowser to begin using the system

### Usage of the project
  - login with username and password and you will be taken to the appropriate user interface
  - depending on your credentials you will be able to view add or approve transactions
