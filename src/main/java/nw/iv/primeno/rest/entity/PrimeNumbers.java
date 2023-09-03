package nw.iv.primeno.rest.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrimeNumbers {
	private int Initial;
	private String calcMethod;
	private List<Integer> Primes;
	private List<String> validationError;


	public int getInitial() {
		return Initial;
	}

	public void setInitial(int initial) {
		Initial = initial;
	}
	
	public String getCalcMethod() {
		return calcMethod;
	}

	public void setCalcMethod(String calcMethod) {
		this.calcMethod = calcMethod;
	}
	
	public List<String> getValidationError() {
		return validationError;
	}

	public void setValidationError(List<String> validationError) {
		this.validationError = validationError;
	}

	public List<Integer> getPrimes() {
		return Primes;
	}

	public void setPrimes(List<Integer> primes) {
		Primes = primes;
	}
}
