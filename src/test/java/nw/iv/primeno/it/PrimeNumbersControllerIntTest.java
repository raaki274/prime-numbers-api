package nw.iv.primeno.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import nw.iv.primeno.PrimenoApplication;
import nw.iv.primeno.rest.entity.PrimeNumbers;

@SpringBootTest(classes = PrimenoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PrimeNumbersControllerIntTest {
	  @LocalServerPort
	  private int port;
	 
	  @Autowired
	  private TestRestTemplate restTemplate;
	  
	  @Test
	  public void TestAppStatus() 
	  {
	    ResponseEntity<PrimeNumbers> re = this.restTemplate
	  	      .getForEntity("http://localhost:" + port + "/primes/25", PrimeNumbers.class);
	  	    assertEquals("200 OK", re.getStatusCode().toString());
	  }
	  
	  @Test
	  public void TestNaiveMethod() 
	  {
		  PrimeNumbers pns = this.restTemplate
		          .getForObject("http://localhost:" + port + "/primes/45", PrimeNumbers.class);
		  
		  assertTrue(pns.getInitial() == 45);
		  assertTrue(pns.getPrimes().size() > 0);
	  }
	  
	  @Test
	  public void TestAsyncMethod() 
	  {
		  PrimeNumbers pns = this.restTemplate
		          .getForObject("http://localhost:" + port + "/primes/20000000", PrimeNumbers.class);
		  
		  assertTrue(pns.getInitial() == 20000000);
		  assertTrue(pns.getPrimes().size() > 0);
	  }
	  
	  @Test
	  public void TestSieEraMethod() 
	  {
		  PrimeNumbers pns = this.restTemplate
		          .getForObject("http://localhost:" + port + "/primes/4500000/siera", PrimeNumbers.class);
		  
		  assertTrue(pns.getInitial() == 4500000);
		  assertTrue(pns.getPrimes().size() > 0);
	  }
	  
	  @Test
	  public void TestSegSieMethodXml() 
	  {
		  PrimeNumbers pns = this.restTemplate
		          .getForObject("http://localhost:" + port + "/primes/1450000/segsie?rsptype=xml", PrimeNumbers.class);
		  
		  assertTrue(pns.getInitial() == 1450000);
		  assertTrue(pns.getPrimes().size() > 0);
	  }
	  
	  @Test
	  public void TestInvalidNumber() 
	  {
		  PrimeNumbers pns = this.restTemplate
		          .getForObject("http://localhost:" + port + "/primes/0", PrimeNumbers.class);
		  
		  assertTrue(pns.getInitial() == 0);
		  assertTrue(pns.getValidationError().get(0).equals("Received invalid number to calculate prime numbers"));
	  }
	  
	  @Test
	  public void TestInvalidCalcMethod() 
	  {
		  PrimeNumbers pns = this.restTemplate
		          .getForObject("http://localhost:" + port + "/primes/1/xyz", PrimeNumbers.class);
		  assertTrue(pns.getInitial() == 1);
		  assertTrue(pns.getValidationError().get(0).equals("Received invalid number to calculate prime numbers"));
		  assertTrue(pns.getValidationError().get(1).equals("Received invalid calc method to calculate prime numbers, it should be any one of: [siera, segsie]"));
	  }
}

