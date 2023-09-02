package nw.iv.primeno.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.calcmethod.impl.CalculationMethodFactory;
import nw.iv.primeno.rest.entity.PrimeNumbers;

@RestController
public class PrimeNumbersController {

	@Autowired
	private Environment env;
	
	@Autowired
	private CalculationMethodFactory cf;
	
	@Autowired
	private AsyncCalculator ac;
	
	private static final Logger logger = LoggerFactory.getLogger(PrimeNumbersController.class);
	
	
    @GetMapping("/primes/{N}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PrimeNumbers> get(@PathVariable int N, @RequestParam(required = false, defaultValue = "json") String rsptype) {
    	logger.info("Entered default calc method: "+N);
    	
		logger.info("Setting response type for: "+rsptype);
		final HttpHeaders httpHeaders = new HttpHeaders();
		if("xml".equals(rsptype))
			httpHeaders.setContentType(MediaType.APPLICATION_XML);
		else
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	
    	PrimeNumbers response = null;
    	List<String> err;
    	CalculationMethod calcMethod;
    	int sliceSize = Integer.parseInt(env.getProperty("MAX_SLICE_NUMBER"));
    	try {    		
    		err = new Validator().validateInput(N); 
    		if(err.size() > 0) {
    			response = new PrimeNumbers();
    			response.setValidationError(err);
    			response.setInitial(N);
    			return ResponseEntity.ok()
  				      .headers(httpHeaders)
  				      .body(response);
    		}
    		
    		if(N>sliceSize) {
    			logger.info("Invoking concurrent algorithm for large number");
    			response = ac.asyncCalculator(N);
    			logger.info("Prime numbers list size: "+response.getPnList().size());
    		} else {
    			calcMethod = cf.getCalcMethod(null);
        		response = calcMethod.calculatePrimeNumbers(N);
        		logger.info("Prime numbers list size: "+response.getPnList().size());
    		}    		
    	} catch(Exception e) {
    		logger.error("Error captured: "+e.getMessage());
    		e.printStackTrace();
    		throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Please try after some time", e);
    	}
    	
    	logger.info("Exiting default calc method");
		return ResponseEntity.ok()
			      .headers(httpHeaders)
			      .body(response);
    }
    
    @GetMapping("/primes/{N}/{method}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PrimeNumbers> get(@PathVariable int N, @PathVariable String method, @RequestParam(required = false) String rsptype) {
    	logger.info("Entered algorithm based calc method for : "+N+" and "+method);

		logger.info("Setting response type for: "+rsptype);
		final HttpHeaders httpHeaders = new HttpHeaders();
		if("xml".equals(rsptype))
			httpHeaders.setContentType(MediaType.APPLICATION_XML);
		else
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	
    	PrimeNumbers response = null;
    	List<String> err;
    	CalculationMethod calcMethod;
    	try {
    		err = new Validator().validateInput(N, method); 
    		if(err.size() > 0) {
    			response = new PrimeNumbers();
    			response.setValidationError(err);
    			response.setInitial(N);
    			response.setCalcMethod(method);
    			return ResponseEntity.ok()
    				      .headers(httpHeaders)
    				      .body(response);
    		}
    		calcMethod = cf.getCalcMethod(CalcMethods.valueOf(method));
    		response = calcMethod.calculatePrimeNumbers(N);
    		logger.info("Prime numbers list size: "+response.getPnList().size());
    	} catch(Exception e) {
    		logger.error("Error captured: "+e.getMessage());
    		e.printStackTrace();
    		throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Please try after some time", e);
    	}
    	logger.info("Exiting algorithm based calc method");
        return ResponseEntity.ok()
			      .headers(httpHeaders)
			      .body(response);
    }
}