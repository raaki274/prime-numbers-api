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

import com.fasterxml.jackson.databind.ObjectMapper;

import nw.iv.primeno.rest.controller.PrimeNumbersController;


@RunWith(SpringRunner.class)
@WebMvcTest(PrimeNumbersController.class)
public class PrimeNumbersControllerTest {
	
    @Autowired
    private MockMvc mvc;
       
    @Test
    public void calPrimeNumberByNaiveMethod() throws Exception 
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/person/{id}", "RK")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("RK"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}