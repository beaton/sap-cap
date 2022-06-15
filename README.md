# sap-cap

Playground for the CAP environment from SAP, a Java Spring Boot based application framework for develping enterprise applications.

Homepage: https://pages.github.tools.sap/erp4sme/landingpage/

Getting started: https://cap.cloud.sap/docs/java/

## Minimum (tested) configuration

1. openjdk 11.0.9.1 2020-11-04 LTS
2. Apache Maven 3.8.5
3. Node 8.3.1

## Build

Although not recommended, to skip tests add: -Dmaven.test.skip=true

If running locally run in development mode you can use AWS credentials locally stored in ~/.aws/credentials folder.

### Debugging

```
mvn clean install
```

java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar target/users-0.0.1-SNAPSHOT.war.

Using Eclipse, you can then be easily connected to the running application through Debug Configurations and creating a Remote Java Application configuration.

### Production

Alternatively to run without debugging, open up a terminal and type the following:

```
mvn spring-boot:run
```

## Run

Once up and running open a web browser: 

1. CDS auto-generated endpoint [https://localhost:8080](http://localhost:8080/)
2. Custom endpoint [https://localhost:8080/catalog](http://localhost:8080/catalog/)

## CI/CD

CI/CD is being supported through a webhook on this repo that posts to the BTP Cockpit: [SAP CAP Continuous Integration and Delivery Service](https://help.sap.com/docs/BTP/65de2977205c403bbc107264b8eccf4b/fe74df55b0f54e99bf6e13a3b53e1db0.html).  Instuctions to add a webhook in Github can be found here: [Add a Webhook in Github](https://help.sap.com/docs/CONTINUOUS_DELIVERY/99c72101f7ee40d0b2deb4df72ba1ad3/090d4aaa9628426b91c90e8284213040.html).  We could just as easily have used AWS Pipeline, Github Actions or many other options - we chose SAP CI/CD as this is an end-to-end sample of CAP. A detailed tutorial can be found here: [Configure and Run a Predefined SAP Continuous Integration and Delivery (CI/CD) Pipeline](https://developers.sap.com/tutorials/btp-app-ci-cd-btp.html)
