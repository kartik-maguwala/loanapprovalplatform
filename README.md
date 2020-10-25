# LoanApprovalPlatform

## Application Requirement and Understanding

* It should implement KYC
* As an end user, I should be able to apply for loan(online)
* As a front officer, I should be able to validate the loan request and can able to approve or reject request.
 Upon appoval, I will ask for approval from the car loan compliance department and risk department.
* As a car loan compliance approving officer, I should be able to approve or reject the loan request.
* As a risk officer, I should be able to approve or reject a loan application and a risk officers’ decision is final and overrides any other approvers (Car loan compliance)
* Based on the final decision, a request should be send to disbursal department to disburse the money
 

## TechStack Used

* Java 11
* Spring Boot
* H2 DB(in memory) for dev environment
* Postgres for prod environment

## Tools Used
* Keycloak
* Sonarqube
* Prometheus
* Grafana

## Prerequisites
* Docker
* Java 11

## Proposed Architecture

![High Level Design](https://raw.githubusercontent.com/kartik-maguwala/loanapprovalplatform/master/images/HighlevelDesign.png)

## Database Design

![Datbase Design](https://raw.githubusercontent.com/kartik-maguwala/loanapprovalplatform/master/images/DB_Diagram.png)

## Run Keycloak Service

There are multiple users(with role) using the application. They are
* Loan Applicant: Who apply for a loan and can be anonymous.
* Front Desk Officer(**ROLE_FRONTDESKOFFICER**): Who perform functionality related to front desk loan department.
* Car Loan Compliance Officer(**ROLE_CARLOANCOMPLIANCE**): Who performs functionality related to car loan compliance.
* Risk Compliance Officer(**ROLE_RISKCOMPLIANCE**): Who performs functionality related to risk compliance.
* Car Loan Disbursal Officer(**ROLE_DISBURSAL**): Who performs functionality related to disbursal.

Run the command to start Keycloak server
```
docker-compose -f src/main/docker/keycloak.yml up
```

To verify, please open http://localhost:9080/

Go to "Administration Console" and use "admin" as username and "admin" as password

Credential used by the application

Client: internal

Secret: 91e20d8f-1678-4d04-8782-f04e1bc19219

| Username  | Password |
| ------------- | ------------- |
| frontdeskuser  | frontdeskuser  |
| carloancomplianceuser  | carloancomplianceuser  |
| riskcomplianceuser  | riskcomplianceuser  |
| disbursaluser  | disbursaluser  |


## Build Jar artifact
To generate Jar artifact, please run

```
/mvnw clean package -Dmaven.test.skip=true
```

The artifact will be generated in "target/loanapprovalplatform-0.0.1-SNAPSHOT.jar"

## Run Application (Dev environment)
To run the application in "dev" environment, please execute
```
java -jar target/loanapprovalplatform-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

## Run Application (Prod environment)
To run the application in "prod" environment, please execute
```
java -jar target/loanapprovalplatform-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## API documentation (available in Dev environment only)
To open Open API documentation, please use http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config 

## Matrics Collection using Prometheus and Grafana ( Dashboard)
In the application, Prometheus endpoint will be available at http://localhost:8080/actuator/prometheus

Before start prometheus, please change ```targets: ['<HOST_IP_ADDRESS>:8080']``` property with your host ip address in the "src/main/docker/prometheus/prometheus.yml"

To check host ip address, please run ```ifconfig | grep inet``` in linux environment.
For example, host IP address is "196.168.0.1". The value to property will be ```targets: ['192.168.0.1:8080']```

Before start grafana, please change ```url: http://<HOST_IP_ADDRESS>:9090``` property with your host ip address in the ```src/main/docker/grafana/provisioning/datasources/datasource.yml```.
For example, host IP address is "196.168.0.1". The value to property will be ```url: http://192.168.0.1:9090```


To run prometheus and grafana, please execute
```
docker-compose -f src/main/docker/monitoring.yml up
```

To verify, if Prometheus running, please browse http://localhost:9090/targets. The state value of the target should be ```UP```

To open the grafana dashboard, browse http://localhost:3000/ and enter "admin" as username "admin" as password.

Click "skip".
Click "Home" from top left area of the dashboard and click on "JVM (Micrometer)".

## Code quality reports

To run sonarqube, please execute
```
docker-compose -f src/main/docker/sonar.yml up
```
To verify, please browse http://localhost:9001/
Please setup sonarqube by creating new project named "LoanApprovalPlatform" and set a token.

To push generate the report, execute
```
./mvnw sonar:sonar \
  -Dsonar.projectKey=LoanApprovalPlatform \
  -Dsonar.host.url=http://localhost:9001 \
  -Dsonar.login=<TOKEN>
```

Note: Sonarqube takes few seconds to generate the report.

## Build and Run docker images

To build docker images of the application, execute

```
./mvnw jib:dockerBuild
```
It will store docker image in your local docker repository. To verify, please execute

```
docker images
```
There should be docker image named ```loanapprovalplatform```

## Working Flow of the application

Note: Make sure keycloak and application is running.

**1. User apply for a car loan**
```
curl -X POST "http://localhost:8080/v1/api/carloan/" -H "accept: */*" -H "Content-Type: application/json" -d "{\"firstname\":\"string\",\"lastname\":\"string\",\"email\":\"abc@example.com\",\"amount\":1,\"pancardno\":\"string\",\"accountno\":\"string\",\"ifsccode\":\"string\",\"bankname\":\"string\",\"address1\":\"string\",\"address2\":\"string\"}"
```
**2. Front desk user, check pending car loan application**

To get token from authentication service using frontdeskuser credential
```
curl --location --request POST 'http://localhost:9080/auth/realms/carloan/protocol/openid-connect/token' \
   --header 'Authorization: Basic aW50ZXJuYWw6OTFlMjBkOGYtMTY3OC00ZDA0LTg3ODItZjA0ZTFiYzE5MjE5' \
   --header 'Content-Type: application/x-www-form-urlencoded' \
   --data-urlencode 'grant_type=password' \
   --data-urlencode 'username=frontdeskuser' \
   --data-urlencode 'password=frontdeskuser'
```
Copy ```access_token``` from the response and pass it in next command.

To check pending car loan application for front desk department
```
curl --location --request \
GET 'http://localhost:8080/v1/api/fd/pending/0/5' \
--header 'Authorization: Bearer <ACCESS_TOOKEN>'
```

**3. Front desk user verify and approve/reject car loan application.**

To Approve the loan application

```
curl --location --request PUT 'localhost:8080/v1/api/fd/<APPLICATION_ID>/approve' \
--header 'Content-Type: application/json' \
--header 'Authorization: bearer <ACCESS_TOOKEN>' \
--data-raw '{
    "notes":"<CUSTOM NOTES>"
}'
```

To Reject the loan application

```
curl --location --request PUT 'localhost:8080/v1/api/fd/<APPLICATION_ID>/reject' \
--header 'Content-Type: application/json' \
--header 'Authorization: bearer <ACCESS_TOOKEN>' \
--data-raw '{
    "notes":"<CUSTOM NOTES>"
}'
```


**4. Carloan compliance officer, check pending car loan application.**

To get token from authentication service using carloancomplianceuser credential.
```
curl --location --request POST 'http://localhost:9080/auth/realms/carloan/protocol/openid-connect/token' \
   --header 'Authorization: Basic aW50ZXJuYWw6OTFlMjBkOGYtMTY3OC00ZDA0LTg3ODItZjA0ZTFiYzE5MjE5' \
   --header 'Content-Type: application/x-www-form-urlencoded' \
   --data-urlencode 'grant_type=password' \
   --data-urlencode 'username=carloancomplianceuser' \
   --data-urlencode 'password=carloancomplianceuser'
```
Copy ```access_token``` from the response and pass it in next command.

To check pending car loan application for car loan compliance department.
```
curl --location --request \
GET 'http://localhost:8080/v1/api/clc/pending/0/5' \
--header 'Authorization: Bearer <ACCESS_TOOKEN>'
```


**5. Carloan compliance officer, approve/reject car loan application.**

To Approve the loan application

```
curl --location --request PUT 'localhost:8080/v1/api/clc/<APPLICATION_ID>/approve' \
--header 'Content-Type: application/json' \
--header 'Authorization: bearer <ACCESS_TOKEN>' \
--data-raw '{
    "notes":"<CUSTOM NOTES>"
}'
```

To Reject the loan application

```
curl --location --request PUT 'localhost:8080/v1/api/clc/<APPLICATION_ID>/reject' \
--header 'Content-Type: application/json' \
--header 'Authorization: bearer <ACCESS_TOKEN>' \
--data-raw '{
    "notes":"<CUSTOM NOTES>"
}'
```


**6. Risk compliance officer, check pending car loan application.**

To get token from authentication service using riskcomplianceuser credential.
```
curl --location --request POST 'http://localhost:9080/auth/realms/carloan/protocol/openid-connect/token' \
   --header 'Authorization: Basic aW50ZXJuYWw6OTFlMjBkOGYtMTY3OC00ZDA0LTg3ODItZjA0ZTFiYzE5MjE5' \
   --header 'Content-Type: application/x-www-form-urlencoded' \
   --data-urlencode 'grant_type=password' \
   --data-urlencode 'username=riskcomplianceuser' \
   --data-urlencode 'password=riskcomplianceuser'
```
Copy ```access_token``` from the response and pass it in next command.

To check pending car loan application for risk compliance department.
```curl --location --request \
GET 'http://localhost:8080/v1/api/rc/pending/0/5' \
--header 'Authorization: Bearer <ACCESS_TOOKEN>'
```

**7. Risk compliance officer, approve/reject car loan application.**

To Approve the loan application

```
curl --location --request PUT 'localhost:8080/v1/api/rc/<APPLICATION_ID>/approve' \
--header 'Content-Type: application/json' \
--header 'Authorization: bearer <ACCESS_TOKEN>' \
--data-raw '{
    "notes":"<CUSTOM NOTES>"
}'
```

To Reject the loan application

```
curl --location --request PUT 'localhost:8080/v1/api/rc/<APPLICATION_ID>/reject' \
--header 'Content-Type: application/json' \
--header 'Authorization: bearer <ACCESS_TOKEN>' \
--data-raw '{
    "notes":"<CUSTOM NOTES>"
}'
```

**7. Loan disbursal officer, disburse loan amount.**

Once risk compliance department approve the loan, it will move to loan disbursal department.


To get token from authentication service using riskcomplianceuser credential.
```
curl --location --request POST 'http://localhost:9080/auth/realms/carloan/protocol/openid-connect/token' \
   --header 'Authorization: Basic aW50ZXJuYWw6OTFlMjBkOGYtMTY3OC00ZDA0LTg3ODItZjA0ZTFiYzE5MjE5' \
   --header 'Content-Type: application/x-www-form-urlencoded' \
   --data-urlencode 'grant_type=password' \
   --data-urlencode 'username=disbursaluser' \
   --data-urlencode 'password=disbursaluser'
```
Copy ```access_token``` from the response and pass it in next command.

To check pending car loan application for risk compliance department.
```curl --location --request \
GET 'http://localhost:8080/v1/api/cld/pending/0/5' \
--header 'Authorization: Bearer <ACCESS_TOOKEN>'
```


To disburse loan, run following command

```
curl --location --request PUT 'http://localhost:8080/v1/api/cld/<APPLICATION_ID>/disburse' \
--header 'Content-Type: application/json' \
--header 'Authorization: bearer <ACCESS_TOKEN>' \
--data-raw '{"disburseAmount":123,"transactionId":"string","branchIfsc":"string","disbursementDate":"2020-10-23"}'
```

## CI-CD pipeline (In Progress)

To build CI/CD pipeline, planning to use Jenkin. Added jenkinfile in the project (not tested).

## Testing

It would be best to follow TDD,BDD for better code quality and test automation. May be in future, I'll add some testing
* Unit tests
* Integration tests
* Functional tests
* Contract tests
 
