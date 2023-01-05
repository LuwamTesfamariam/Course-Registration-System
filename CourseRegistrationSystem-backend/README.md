# CS544CourseRegistrationSystem

## Technologies
- Spring boot
- SMTP Server (Gmail)
- AWS S3 for cloud just if we can
- JWT for security
- PostgreSQL.(AWS/docker) or H2 database
- Kafka for messaging

## Must Have 
1. Send emails asynchronously. 
2. Use a JMS/Kafka message queue and a DB table 
3. Send an email reminder 8 and 4 hours prior to the end of the registration period 
4. Use Git for code repo 
5. Have more than one REST service (which means your services are calling one another) 

## Extra Credit 
1. Spring Security using token based authentication (JWT) 
2. Web layer of your API using WebFlux and non-blocking IO 
3. Deploy your application to the cloud (AWS, Google, Azure, Cloud Foundry, ...) 
4. Create an automated pipeline for CI/CD (continuous integration/continuous delivery) 
5. Use Spring Cloud Feign for your service calls (https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html)


