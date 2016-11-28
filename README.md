# GreedyDB

## Task

(See here for instructions on challenge)[]

## User Stories (US)

US1

User can access all records of a specific stream via restul api ie /import/{streamName}

US2

User can access a given record given the timestamp and streamName, and return the value given by the field ie /query/{streamName}/{timestamp}/{jsonPath}

US3

User can access the latest record of a given streamName and return the value of the field ie /query/{streamName}/latest/{jsonPath}

US4

User can access the oldest record of a given streamName and return the value of the field ie /query/{streamName}/oldest/{jsonPath}

US5

User is given error messagee (ie 404) if no matches in database

US6

User will only be able to access records that is within 30 minutes of the lastest record in the database

## Approach

I made the assumption that the database was already setup, and it was being given data through another server. Thus I just needed to access this database through the RESTful apis.

I decided to use MongoDB as my database, due to the contents of the data being json objects and embedded json objects. Plus the fields were different for each streamName, which would not suit using relational databases.

I decided to use Jersey to handle the routes. MongoDB driver to handle the connection with the database. Moxey to convert my models of stored data into Json for output.

I used the MVC pattern, where the Resouce files are the controllers, which asks the Service files to handle the interaction between the model and the database (ie make a db connection, query and store in data in model objects) and return the required output (ie json or string value). The view was not handled as it was a json api.

As Java is fairly new to me, I decided to learn how to build a restful api and then how to use the testing framework (junit and jersey testing). I managed to do spike the web app, but took longer than expected, thus I did not have enough time for testing the everything (ie database). Then I started from scratch and did a test driven approach but did not manage to finish all my testing.

## Completed

I managed to complete a spiked version of the problem, US1 to US4.

## Issues

Current issues I am facing are

- return a response for my controllers are not working. This led to me not being able to reduce the two paths for Query to one and not being able to print out 404 pages for empty database queries. ie having one method for path /import/{streamName}. This led me to the MessageBodyWriter issue which I was not able to resolve.

I kept the code for the controller route which was not working in the ImportResource class.

- This works for the two different types of streams, click and employer, but will have to constantly add new code to the service, model, dbconnection and resource files to accept different types of streams. So it is not easily exentendable.

- Mongdb driver methods I am using are deprecated. Unfortunately, aiming to use the new methods, I had issues with implementing fully this new version with extracting 'click' records from database. I decided to stick with the deprecated methods and keep the code clean as I was able to extract out the mongodb connection code to a method.


## To Do

- Test service and User stories 2 to 6
- Tests for the database
  - mocking the data
- Use new methods for mongodb
- Finish US5 and US6
  - Errors for no results on query
  - do not consider results which are 30 minutes older than the latest record in database.
- Get import/{streamName} working using one method instead of two
- Feature testing
- Return mongodb id in json reponse

## Technology

- Java 1.8.0_91 (Server side language)
- Tomcat 8.5 (to run the app)
- MongodDB 3.2.10 (to store the greedyDB)
- Jersey (to handle the RESTful routes)
- Maven (to handle all dependencies of libraries)
- MongoDB driver (to connect mongo with java app)
- Junit (for tests and matchers)
- Jersey testing framework (handle testing server)
- Hamcrest (testing matchers)

## To run

This code is zipped up, use an unzipper and extract to hard drive.

Setup database

1. install mongodb (use homebrew for mac)
2. start mongodb  ($ mongod)
3. Open new terminal window, type mongo ($ mongo)
4. Run commands given in file (database.txt) in the mongo shell prompt, to create database, collection and documents.
5. https://docs.mongodb.com/getting-started/shell/import-data/

I used Eclipse EE (neon.1) to develop this app.

1. Import into eclipse
2. update maven
3. click project folder, and  run as server, choose tomcat 8.5, select next, select project and remove other projects.
4. Once browser is up, enter url into address bar

The program is run from the ImportResource and QueryResource files

To run the test, click on the folder `src/test/java` and run as junit test