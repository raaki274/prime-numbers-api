package nw.iv.primeno.rest.calcmethod.impl;

import java.util.ArrayList;
import java.util.List;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.entity.PrimeNumbers;

public class NaiveMethod implements CalculationMethod {
	
	public PrimeNumbers calculatePrimeNumbers(long n) {
		PrimeNumbers pns = new PrimeNumbers();
		List<Long> pnList = new ArrayList<>();
        for(long i=2;i<=n;i++) {
            if (isPrimeNo(i))
            	pnList.add(Long.valueOf(i));
        }
        pns.setPnList(pnList);
        pns.setN(n);
        
		return pns;
	}

    public boolean isPrimeNo(long nmbr) {
        for(long i=2;i<=nmbr/2;i++) {
        	if(nmbr%i == 0) return false;
        }
        return true;
	}
}
