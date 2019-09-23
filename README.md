Rabobank code assigment
---

### Test

Run `mvn clean test` on root module for unit & integration test.

### Running Instructions
You can run an application in several ways.
#### Maven
If Executing the `mvn spring-boot:run` command It will triggers the download of Apache Tomcat and initializes the startup of Tomcat.

#### Maven Wrapper
You can run a below command from a `api` module
`./mvnw spring-boot:run`
### Database

Make sure postgres is running before running an application. Run a docker compose

`docker-compose up -d` 

### Testing Url
Available Endpoints

#### Get All persons
`http://localhost:8080/persons`

#### Create person
The below `curl` request will create a person.

``curl -X POST \
    http://localhost:8080/persons \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Length: 112' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'cache-control: no-cache' \
    -d '{
    "firstname": "Lubna",
    "lastname": "Afzal",
    "dob": "01-01-1970",
    "age":18,
    "address": "La Palma 89"
  }``

#### Update Person
The below `curl` request will update a person.

``curl -X PUT \
     http://localhost:8080/persons \
     -H 'Authorization: Basic cmFib2Jhbms6cGFzc3dvcmQ=' \
     -H 'Content-Type: application/json' \
     -H 'Postman-Token: da064dec-98ee-4919-9ff0-a631fbbda45e' \
     -H 'cache-control: no-cache' \
     -d '{
     "id":1,
     "firstname": "Nehal",
     "lastname": "Afzal",
     "dob": "01-02-1981",
     "age": 20,
     "address": "La Palma 89"
   }``

#### Get a person
`http://localhost:8080/persons/1/`




