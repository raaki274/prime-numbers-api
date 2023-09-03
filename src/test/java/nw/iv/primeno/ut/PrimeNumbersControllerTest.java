package nw.iv.primeno.ut;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import nw.iv.primeno.rest.controller.PrimeNumbersController;

@RunWith(SpringRunner.class)
@WebMvcTest(PrimeNumbersController.class)
public class PrimeNumbersControllerTest {
	
    @Autowired
    private MockMvc mvc;
       
    @Test
    public void calcPrimeNumberByNaiveMethod() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}", 70)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.initial").value(70))
          .andExpect(MockMvcResultMatchers.jsonPath("$.primes").isArray());
    }
    
    @Test
    public void calcPrimeNumberByAsyncProcess() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}", 20000000)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.initial").value(20000000))
          .andExpect(MockMvcResultMatchers.jsonPath("$.primes").isArray());
    }
    
    @Test
    public void calcPrimeNumberBySieEraAlgorithm() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}/{method}", 2500, "siera")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.initial").value(2500))
          .andExpect(MockMvcResultMatchers.jsonPath("$.primes").isArray());
    }
    
    @Test
    public void calcPrimeNumberBySegSieAlgorithm() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}/{method}", 2500000, "segsie")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.initial").value(2500000))
          .andExpect(MockMvcResultMatchers.jsonPath("$.primes").isArray());
    }
    
    @Test
    public void validationErrorForInvalidNumber() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}", -1)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.initial").value(-1))
          .andExpect(MockMvcResultMatchers.jsonPath("$.validationError").value("Received invalid number to calculate prime numbers"));
    }
    
    @Test
    public void validationErrorForInvalidAlgorithm() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}/{method}", 65, "abc")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.calcMethod").value("abc"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.validationError").value("Received invalid calc method to calculate prime numbers, it should be any one of: [siera, segsie]"));
    }
    
    @Test
    public void responseTypeXml() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}?rsptype=xml", 25)
          .accept(MediaType.APPLICATION_XML))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.xpath("//PrimeNumbers/initial").number(Double.valueOf(25)))
          .andExpect(MockMvcResultMatchers.xpath("//PrimeNumbers/primes/primes").exists());
    }
    
    @Test
    public void responseTypeXmlBySegSieAlgorithm() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}/{method}?rsptype=xml", 4500000, "segsie")
          .accept(MediaType.APPLICATION_XML))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.xpath("//PrimeNumbers/initial").number(Double.valueOf(4500000)))
          .andExpect(MockMvcResultMatchers.xpath("//PrimeNumbers/primes/primes").exists());
    }
    
    @Test
    public void validationErrorXmlForInvalidNumber() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}?rsptype=xml", -1)
          .accept(MediaType.APPLICATION_XML))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.xpath("//PrimeNumbers/initial").number(Double.valueOf(-1)))
          .andExpect(MockMvcResultMatchers.xpath("//PrimeNumbers/validationError/validationError").string("Received invalid number to calculate prime numbers"));
    }
    
    @Test
    public void validationErrorXmlForInvalidAlgorithm() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/primes/{N}/{method}?rsptype=xml", 65, "abc")
          .accept(MediaType.APPLICATION_XML))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.xpath("//PrimeNumbers/calcMethod").string("abc"))
          .andExpect(MockMvcResultMatchers.xpath("//PrimeNumbers/validationError/validationError").string("Received invalid calc method to calculate prime numbers, it should be any one of: [siera, segsie]"));
    }
}