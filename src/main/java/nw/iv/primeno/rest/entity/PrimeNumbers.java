package nw.iv.primeno.rest.entity;

import java.util.List;

public class PrimeNumbers {
	private int n;
	private List<Integer> pnList;
	private List<String> validationError;

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public List<Integer> getPnList() {
		return pnList;
	}

	public void setPnList(List<Integer> pnList) {
		this.pnList = pnList;
	}
	
	public List<String> getValidationError() {
		return validationError;
	}

	public void setValidationError(List<String> validationError) {
		this.validationError = validationError;
	}
}
