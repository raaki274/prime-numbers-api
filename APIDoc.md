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

## Note
```
This API is developed on Mac OS using Eclipse IDE with required dependencies like JDK, Maven and Git provided by Eclipse, and with required cofigs/settings the can be modified and enhanced further using any other IDEs like IntelliJ. For running this API on you local machine you will require `Java 17 or above, Maven 4 and Git desktop/bash/client` installed on your machine.
```

