# primeno-api documentation
This is a REST based API will respond with the list of prime numbers calculated between 2 and N for the given Number, default response will be in JSON format, and it can respond in XML format when requested.

## Tools & Tech Stack
* Java 17.x.x
* Spring Boot 3.1.3
* Embedded Tomcat Web Server
* Maven 4
* Junit
* Jackson Faster XML
* GitHub

This API is deployed on `Render` and it can be accessed through the URL `https://primenos.onrender.com/primes+URIs` and you can find the URIs from the list of usage scenario given below,

## Usage Scenarios
* `GET` `URL/99`
  * Retrieves the list of prime numbers calculated between `2 and 99` using naive method
* `GET` `URL/100000/siera`
  * Retrieves the list of prime numbers calculated between `2 and 100000` using Sieve of Eratosthenes method
* `GET` `URL/2500000/segsie`
  * Retrieves the list of prime numbers calculated between `2 and 2500000` using Segmented Sieve method and
* `GET` `URL/1000001`
  * Retrieves the list of prime numbers calculated between `2 and 1000001` using Segmented Sieve method and asynchronous threads and this is the fastest all the methods used in this API; when the given input `N > 1000000` the API will spawn `(N/1000000)+1` number of async threads and will aggregate the list of prime numbers received from each thread, it will sort the list before it return the response.
* `GET` `URL/45?rsptype=xml`
  * Retrieves the list of prime numbers calculated between 2 and 45 using naive method and the response will be in XML format
* `GET` `URL/30000/siera?rsptype=xml`
  * Retrieves the list of prime numbers calculated between 2 and 30000 using Sieve of Eratosthenes method and the response will be in XML format
* `GET` `URL/1500000/segsie?rsptype=xml`
  * Retrieves the list of prime numbers calculated between 2 and 1500000 using Segmented Sieve method and the response will be in XML format
* `GET` `URL/1`
  * Returns validation error message as the given input `N < 2`
* `GET` `URL/0?rsptype=xml`
  * Returns validation error message in XML format as the given input `N < 2` and requested response type is XML
* `GET` `URL/25/xyz`
  * Returns validation error message as the given input `Calc Method` is invalid; valid calc methods are `siera` for `Sieve of Eratosthenes` and 'segsie` for `Segmented Sieve`

## Sample response messages
* Success response (JSON)
```
{
    "initial": 10,
    "primes": [
        2,
        3,
        5,
        7
    ]
}
```

* Validation error response for invalid number (JSON)
```
{
    "validationError": [
        "Received invalid number to calculate prime numbers"
    ],
    "initial": 1
}
```

* Validation error response for invalid calc method (JSON)
```
{
    "calcMethod": "abc",
    "validationError": [
        "Received invalid calc method to calculate prime numbers, it should be any one of: [siera, segsie]"
    ],
    "initial": 5
}
```

* Validation error response for invalid number and calc method (JSON)
```
{
    "calcMethod": "abc",
    "validationError": [
        "Received invalid number to calculate prime numbers",
        "Received invalid calc method to calculate prime numbers, it should be any one of: [siera, segsie]"
    ],
    "initial": 1
}
```

* Success response (XML)
```
<PrimeNumbers>
    <initial>8</initial>
    <primes>
        <primes>2</primes>
        <primes>3</primes>
        <primes>5</primes>
        <primes>7</primes>
    </primes>
</PrimeNumbers>
```

* Validation error response for invalid number and calc method (JSON)
```
<PrimeNumbers>
    <calcMethod>xyz</calcMethod>
    <validationError>
        <validationError>Received invalid number to calculate prime numbers</validationError>
        <validationError>Received invalid calc method to calculate prime numbers, it should be any one of: [siera, segsie]</validationError>
    </validationError>
    <initial>1</initial>
</PrimeNumbers>
```

## Running and modifying the API
The packaged executable `primeno-0.0.1-SNAPSHOT.jar` is available in the repository under the directory `/snapshot`, you can download it and run it locally if you have JRE 17 on your machine; run the command `java -jar primeno-0.0.1-SNAPSHOT.jar` to start the application on the port number `8080` and you can access the application using the URL: `http://localhost:8080/primes`

This API is developed on Mac OS using Eclipse IDE with required dependencies like JDK, Maven and Git Client provided by Eclipse, with required cofig/settings, API code can be modified, enhanced, compiled and executed further using any other IDEs like IntelliJ supporting Java development.

For changing and running this API on your local machine without using any IDE, you will require `Java 17 or above, Maven 4 and Git desktop/bash/client` installed on your machine and you must be familiar with Java, Spring Boot and Maven commands.
