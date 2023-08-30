package nw.iv.primeno.rest.entity;

import java.util.List;

public class PrimeNumbers {
	private long n;
	private List<Long> pnList;
	private List<String> validationError;

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public List<Long> getPnList() {
		return pnList;
	}

	public void setPnList(List<Long> pnList) {
		this.pnList = pnList;
	}
	
	public List<String> getValidationError() {
		return validationError;
	}

	public void setValidationError(List<String> validationError) {
		this.validationError = validationError;
	}
}
