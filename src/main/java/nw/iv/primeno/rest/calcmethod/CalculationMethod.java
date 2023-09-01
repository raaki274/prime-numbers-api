package nw.iv.primeno.rest.calcmethod;

import nw.iv.primeno.rest.entity.PrimeNumbers;

public interface CalculationMethod {
	public PrimeNumbers calculatePrimeNumbers(int n);
	public PrimeNumbers calculatePrimeNumbers(int low, int high);
}
