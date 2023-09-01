package nw.iv.primeno.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
    @GetMapping("/primes/{N}")
    @ResponseStatus(HttpStatus.OK)
    public PrimeNumbers get(@PathVariable int N) {
    	System.out.println("Inside GET for naive method: "+N);
    	PrimeNumbers response = null;
    	List<String> err;
    	CalculationMethod calcMethod;
    	try {
    		err = new Validator().validateInput(N); 
    		if(err.size() > 0) {
    			response = new PrimeNumbers();
    			response.setValidationError(err);
    			return response;
    		}
    		
    		if(N>10000000) {
    			response = ac.asyncCalculator(N);
    		} else {
    			calcMethod = cf.getCalcMethod(null);
        		response = calcMethod.calculatePrimeNumbers(N);
        		System.out.println("pn list size:"+response.getPnList().size());
    		}

    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Please try after some time", e);
    	}
        return response;
    }
    
    @GetMapping("/primes/{N}/{method}")
    @ResponseStatus(HttpStatus.OK)
    public PrimeNumbers get(@PathVariable int N, @PathVariable String method) {
    	System.out.println("Inside GET for calc method: "+N+" and "+method);
    	PrimeNumbers response = null;
    	List<String> err;
    	CalculationMethod calcMethod;
    	try {
    		err = new Validator().validateInput(N, method); 
    		if(err.size() > 0) {
    			response = new PrimeNumbers();
    			response.setValidationError(err);
    			return response;
    		}
    		calcMethod = cf.getCalcMethod(CalcMethods.valueOf(method));
    		response = calcMethod.calculatePrimeNumbers(N);
    		System.out.println("pn list size:"+response.getPnList().size());
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Please try after some time", e);
    	}
        return response;
    }
    
}