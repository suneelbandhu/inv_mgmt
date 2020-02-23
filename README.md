Inventory-Management-System

This application was designed to develop an in memory inventory management system.

##Installation
####Dependencies
Tomcat
Swagger
STS
Java 8
####Configuration
server.port=8080

Download the zip file and unzip it. Import it as an existing maven project in STS.

After importing into STS , run the application it will start running into port number 8080.

Goto browser and open the following below given link it will list down all the available API:

http://localhost:8080/swagger-ui.html#/inventory-controller


####Inventory id for each inventory will be unique thorugh out the application lifecycle.
Following API's are implemented as a part of this project.
1)add inventory.
2)get inventory.
3)list inventory between a given range of date's.
4)delete inventory.

test API request body: 
add inventory sample
              {
                "cost": 1,
                "count": 1,
                "createdOn": "5020-02-23T13:27:20.125Z",
                "inventoryId": "1",
                "name": "inv-1",
                "status": true,
                "updatedOn": "5020-02-23T13:27:20.125Z"
              }
