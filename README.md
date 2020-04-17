# Demo API 

This project is an API demo for Netheos entrance test created by Nicolas Sanial.
I made this in 24-26h, the hardest part for me was to work with gradle cause that was the first time I use this repo/build manager.

## Environment

Gradle v6.3 / Java v8 / MySQL 5.7.21

## Dependencies

To create this API, I used :

### Framework SpringBoot (latest version)

To abstract all spring classic configuration and work with dependency injection.

### Hibernate JPA (latest version)

ORM witch is simple to use and allow creating custom secure query to interact with DB.

### Spring Security (latest version)

I used spring security to manage the security layer. I make the choice to use Basic Auth to secure API. This is not the best way to secure an API. I tried to implement OAuth2, but I never try to implement it before. This could take me more time to do.

### Junit Jupiter latest version and Mockito (latest version)

Allowing to create all unit test for every type of repository/service/controller. Some unit test are ignored because with ``gradle build`` they are not passing with H2 config. I don't know why because when I run them with IntelliJ, they pass (Option : Run test using Gradle (Default)). 

### H2 database (latest version)

To simulate a fake database, make me able to execute unit test in other env than real DB.

## Test the API

Clone the project on "master" branch :
`` git clone https://github.com/nicolas-sanial/api-netheos.git``

- Create a database named netheos_api, make sur your MySql run on port 3306 (if no modify ``application.propoerties``).

- Open the project in IntelliJ and setup mysql username and password in ``src/main/ressources/application.properties``.

- (Optional) You can uncomment the code in DemoapiApplication to generate some datas directly in DB, but this method have to be executed only one time, else this will generate duplicate exceptions. So comment it again after first usage.

There are two ways to test the project, using directly intelliJ or deploy the war in a tomcat server.

#### First way, use directly IntelliJ :

- With IntelliJ, run the project with gradle option of  ``demoapi -> Tasks -> application -> bootRun``. This will generate the DB structure.

- Open postman and import the file ``demoapi/postman/DEMOAPI.postman_collection.json``. Now you can use the imported request to directly test the different API.

#### Second way, deploy in tomcat (v9+) :

- Go to project folder and run ``gradle build`` then put the generated war ``demoapi/build/libs/demoapi-"version".war`` in the webapps tomcat folder.

- Take the file ``src/main/ressources/application.properties`` that you have modified, and paste it in tomcat conf folder (or env folder if already configured in ``conf/context.xml``)

- Make sur your tomcat running on port 8080, rename the war in webapp in ``demoapi.war`` and run the tomcat with ``sh bin/catalina.sh run`` or ``sh bin/startup.sh``. The DB structure will be generated.

- Open postman and import the file ``demoapi/postman/DEMOAPI.postman_collection.json``. Because you are using Tomcat, you have to add the war name in the URL of every request like ``localhost:8080/demoapi/api/faq/search`` for example. The requests should work.


Finally I would like to thank you to give me the chance to try Netheos test. I hope that we will have the opportunity to talk about my code, and the way in which I answered your test.

Best Regards,

Nicolas Sanial.