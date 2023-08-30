package nw.iv.primeno.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {
	
	public List<String> validateInput(long N) {
		
		List<String> errList = new ArrayList<>();
		if(N<=1)
			errList.add("Received invalid number to calculate prime numbers");
		
		return errList;
	}

	public List<String> validateInput(long N, String calcMethod) {
		
		List<String> errList = validateInput(N);
 		List<CalcMethods> methods = Arrays.asList(CalcMethods.values());
		if(methods.toString().indexOf(calcMethod) < 0)
			errList.add("Received invalid calc method to calculate prime numbers, it should be any one of: "+methods.toString());
		
		return errList;
	}
}
