# Tech test Alejandro Rojas
This is an Spring Boot application.
## Getting started
To run the application go to the root of the project, run:
```jshelllanguage
mvn spring-boot:run
```
To run the tests, run:
```jshelllanguage
mvn test
```
## Features
This application serves multiple end points to fulfill all the acceptance criteria

Below is the list of cURL snippets to call the different end points:
### createAddressBook
```jshelllanguage
curl -X GET \
  http://localhost:8090/api/addressBook/createAddressBook \
```
### addContact
```jshelllanguage
curl -X POST \
  http://localhost:8090/api/addressBook/addContact/1 \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "test",
	"phoneNumber": "1234567890"
}'
```
### removeContact
```jshelllanguage
curl -X DELETE \
  http://localhost:8090/api/addressBook/removeContact/1 \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "test",
	"phoneNumber": "1234567890"
}'
```
### getContacts
```jshelllanguage
curl -X GET \
  http://localhost:8090/api/addressBook/getContacts/1 \
```
### getAllGlobalUniqueContacts
```jshelllanguage
curl -X GET \
  http://localhost:8090/api/contact/getAllGlobalUniqueContacts \
```