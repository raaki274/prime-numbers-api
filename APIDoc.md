# primeno-api documentation
This is a REST based API will respond with list of prime numbers from 2 to N for the given Numbers in JSON format by default and it can respond in XMl format as well based on the input.

## Tools & Tech Stack
* Java 17.x.x
* Spring Boot 3.1.3
* Embedded Tomcat Web Server
* Maven 4
* Junit
* Jackson Faster XML
* GitHub

This API is deployed on Render and it cann be accessed through the URL https://primenos.onrender.com/primes and the URIs from the user scenarios given below,

## User Scenarios
* `GET /99`
  * Retrieves the list of prime numbers calculated between `2 and 99` using naive method
* `GET /100000/siera`
  * Retrieves the list of prime numbers calculated between `2 and 100000` using Sieve of Eratosthenes method
* `GET /2500000/segsie`
  * Retrieves the list of prime numbers calculated between `2 and 2500000` using Segmented Sieve method and
* `GET /1000001`
  * Retrieves the list of prime numbers calculated between `2 and 1000001` using Segmented Sieve method and aysynchrnous threads and this is the fastest all the methods used inthis API; when the given input `N > 1000000` the API will spawn `(N/1000000)+1` number of async threads and will agrregate the list of prime numbers received from each thread, it will sort the list before it return the response.
* `GET /45?rsptype=xml`
  * Retrieves the list of prime numbers calculated between 2 and 45 using naive method and the response will be in XML format
* `GET /30000/siera?rsptype=xml`
  * Retrieves the list of prime numbers calculated between 2 and 30000 using Sieve of Eratosthenes method and the response will be in XML format
* `GET /1500000/segsie?rsptype=xml`
  * Retrieves the list of prime numbers calculated between 2 and 1500000 using Segmented Sieve method and the response will be in XML format
* `GET /1`
  * Returns validation error message as the given input `N < 2`
* `GET /0?rsptype=xml`
  * Returns validation error message in XML format as the given input `N < 2` and requested response type is XML
* `GET /25/xyz`
  * Returns validation error message as the given input `Calc Method` is invalid; valid calc methods are `siera` for `Sieve of Eratosthenes` and 'segsie` for `Segmented Sieve`

## Sample response messages
* Success response (JSON)
`{
    "initial": 10,
    "primes": [
        2,
        3,
        5,
        7
    ]
}`

* Validation error response for invalid number (JSON)
`{
    "validationError": [
        "Received invalid number to calculate prime numbers"
    ],
    "initial": 1
}`

* Validation error response for invalid calc method (JSON)
`{
    "calcMethod": "abc",
    "validationError": [
        "Received invalid calc method to calculate prime numbers, it should be any one of: [siera, segsie]"
    ],
    "initial": 5
}`

* Validation error response for invalid number and calc method (JSON)
`{
    "calcMethod": "abc",
    "validationError": [
        "Received invalid number to calculate prime numbers",
        "Received invalid calc method to calculate prime numbers, it should be any one of: [siera, segsie]"
    ],
    "initial": 1
}`




## Prerequisites
    1. Have JDK 11 installed in your machine and set JAVA_HOME path (in-memory database JSONDB is depending on JDK 11)
    
    2. Have Maven installed in your machine and verify Maven commands are working in CLI
    
    3. Have Docker installed in your machine and ensure Docker is running and you can execute Docker CLI commands
    
    4. Have Postman client installed in you machine for submitting REST requests with GET, POST, PUT and DELETE methods

## Steps to setup the application and run

#### Clone the application code to your local directory
    https://github.com/raaki274/persons.git

#### Create directory for saving collections into JSONDB an in-memory database
    1. Go to your home path $HOME in Linux and Mac and %HOMEPATH% in Windows machines
    
    2. Create a directory with name "jsondb"
    
    3. Note: JSONDB components used in the application will store the JSON collections under this location.
    
#### Build packaging and running the application locally
    1. Go to the root/parent directory of the application codebase where the pom.xml is available inside your GIT clone directory
    
    2. Run the following command to build-package your application
       > mvn package
       
    3. Once the build is successful, run the following Java command to run the application
       > java -jar ./target/person-0.0.1-SNAPSHOT.jar
       
    4. The app will start running at - http://localhost:8080, check if it is started and running successfully
    
    5. We are going to Dockerize the application in the next section, you can now stop the application by hitting Ctrl + C
    
#### Dockerizing the application
    1. Run the below command from the application's parent directory for building Docker image
        > docker build -t person-app .
    
    2. Run the below command for running the application image inside Docker container
        > docker run -p 8080:8080 person-app
    
    3. Application image will start and run at port number 8080 inside Docker container

#### Inputs for Testing the application
The app defines following CRUD APIs,

    GET     /ebitest/person/{id}
    POST    /ebitest/person
    PUT     /ebitest/person
    DELETE  /ebitest/person/{id}

Sample inputs for the operations,

URL and JSON input for adding a person 

    POST URL: http://localhost:8080/ebitest/person
    
    {
        "first_name": "John",
        "last_name": "Keynes",
        "age": 29,
        "favourite_colour": "red"
    }

URL with input for retrieving a person, here first_name is the id for retrieving a person
    
    GET URL: http://localhost:8080/ebitest/person/John
    
    
URL and JSON input for updating a person
    
    PUT URL: http://localhost:8080/ebitest/person
    
    {
        "first_name": "John",
        "last_name": "Kelleys",
        "age": 33,
        "favourite_colour": "black"
    }

URL with input for deleting a person

    DELETE URL: http://localhost:8080/ebitest/person/John
    
    Note: No JSON request body required
    
#### Testing the application via Postman
    1. Run the Postman client
    
    2. Open new request tab by clicking on "+ New" button
    
    3. To add person, select request method POST and paste the URL http://localhost:8080/ebitest/person
    
    4. Click on Headers tab, add the following key value param Content-Type = application/json
    
    5. Click on Body tab, select 'raw' option, paste the sample JSON input from the above section
    
    6. Hit 'Send' button for submitting the request
    
    7. For testing all four operations change the request methods and URLs as per the sample input section above and
       use the respective sample JSON inputs
       
### Thanks for your time spent exploring this, cheers!!


