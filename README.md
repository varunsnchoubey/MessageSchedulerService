# MessageSchedulerService
The MessageSchedulerService Project has been developed with Springboot RESTAPI Microservice.
  - To run this project  import project as maven and do maven clean and maven install.
  - Run the springboot project and the project starts on port 8082
  - The project uses MYSQL Database and at the time of initializing, it checks if the connection to MYSQL Database is   successfull. 
  - The project mysql_db.sql which can be used to create schema MessageSchedulerDB and Table messagedetails in MYSQL Database.
  - The project has MessageDetailEntity that is used to persist Message(String), Date(String), time(String) and timezone(String) in messagedetails table.
 
# Running as a packaged application
```sh
java -jar target/messagescheduler-0.0.1-SNAPSHOT.jar
```
# API to schedule message:
#### url: 
```sh
http://localhost:8082/message
```
#### headers:
```sh
Content-Type : application/json
```
#### Request Body:
```sh
{   
    "message":"Hello! This is a sample message",
    "time": "08:50:46 pm",
    "date":"10/11/2020",
    "timeZone" : "America/New_York"
}
```

#### Request Payload Format:

```sh
message (String)  | Required     : Message should not be null, empty or blank. 
time (String)     | Required     : Time must be in hh:mm:ss am/pm format.
date (String)     | Required   : date must be in dd/MM/yyyy format.
timeZone (String) | Optional    : TimeZone should be valid, defaults to server/jvm timezone.
```
#### Successful scheduling of Message produces following Response:
```sh
status: 202 Accepted
```
#### Sample Message is printed in the console:
```sh
Hello! This is a sample message
```
#### Sample Errors in Response Body:
  - Following Errors are returned if time, timezone or message has errors:
```sh
{
    "status": "BAD_REQUEST",
    "errors": "Time must be in hh:mm:ss am/pm format"
}
```
