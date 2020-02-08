# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)


## Run the project
 * Install docker
 * build using ``mvn clean install``
 * run the database using ``docker run -p5432:5432 -e POSTGRES_PASSWORD=ticketing -e POSTGRES_USER=ticketing -e POSTGRES_DB=ticketing -d postgres``
 * run the service java -jar boaty-service/target/boaty-service-1.0-SNAPSHOT.jar
 * dev env setup: ``mvn antrun:run@set-default-properties docker:start process-exec:start@start-boaty-service docker:stop -DinteractiveMode=true``
