package nw.iv.primeno.rest.calcmethod.impl;

import java.util.ArrayList;
import java.util.List;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.entity.PrimeNumbers;

public class SieveOfEratosthenesMethod implements CalculationMethod {
	
	private long[] primeNos = new long[1000001];
	
	public PrimeNumbers calculatePrimeNumbers(long n) {
		PrimeNumbers pns = new PrimeNumbers();
		List<Long> pnList = new ArrayList<>();
		SieveOfEratosthenes(n);
		for (int i=1;i<=n;i++) {
			if (i == 2)
				pnList.add(Long.valueOf(i));
			else if (i%2 == 1 && primeNos[i/2] == 0)
				pnList.add(Long.valueOf(i));
		}
        pns.setPnList(pnList);
        pns.setN(n);
		
		return pns;
	}
	
	private void SieveOfEratosthenes(long n)
	{
		primeNos[0]=1;
		for (int i=3;i*i<=n;i+=2) {
			if (primeNos[i/2] == 0) {
				for (int j=3*i;j<=n;j+=2*i)
					primeNos[j/2] = 1;
			}
		}
	}
}
