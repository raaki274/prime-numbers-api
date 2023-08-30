package nw.iv.primeno.rest.calcmethod.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.entity.PrimeNumbers;

public class SieveOfSundaramMethod implements CalculationMethod {
	
	public PrimeNumbers calculatePrimeNumbers(long n) {
		PrimeNumbers pns = new PrimeNumbers();
		long nNew = (n-1)/2;
	    boolean marked[] = new boolean[(int) (nNew+1)];
	    Arrays.fill(marked, false);
	    for (int i = 1; i <= nNew; i++) {
	    	for (int j = i; (i + j + 2 * i * j) <= nNew; j++) {
	    		marked[i + j + 2 * i * j] = true;
	    	}
	    }

	    List<Long> pnList = new ArrayList<>();
	    if (n > 2)
	    	pnList.add(Long.valueOf(2));
	 
	    long prime = 0;
	    for (int i=1;i<=nNew;i++) {
		    if (marked[i] == false) {
		    	prime = 2*i+1;
		    	pnList.add(Long.valueOf(prime));
		    }
	    }
        pns.setPnList(pnList);
        pns.setN(n);
	    
		return pns;
	}

}
