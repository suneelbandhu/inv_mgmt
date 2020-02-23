Inventory-Management-System


This application was designed to develop and in memory inventory management system.

##Installation
####Dependencies
Apache
Swagger
STS
Java 8
####Configuration

Download all zip file and unzip it and import it as a existing maven project in STS.

server.port=8080

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
