# URL GET Requests

URL Get Requests - Command Line App - https://github.com/RuthKirby/url-request-app

## Running the .jar

There is an executable version in the app-jar folder. This can be 
launched from the command line:
`java -jar prog_exercise.jar`

If the command is followed by Strings (i.e a string of new line delimited URLs) the app
will process these.

## Decisions
* In the docs folder there is a file Plan.pdf that was drawn up before coding that outlines my approach.

## Built with

* [Maven](https://maven.apache.org/) - Management of dependencies
* JUnit - Unit Testing
* Apache HTTPClient - GET Requests
* Apache Commons Validator - Validating URL addresses
* Jackson JSON - For formatting into JSON
* Mockito - For mocking GET Request for offline unit testing
