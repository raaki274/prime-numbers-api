package nw.iv.primeno.rest.calcmethod.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.entity.PrimeNumbers;

@Service
public class NaiveMethod implements CalculationMethod {
	
	@Override
	@Cacheable(value = "primenumbers", key = "#n")
	public PrimeNumbers calculatePrimeNumbers(int n) {
		PrimeNumbers pns = new PrimeNumbers();
		List<Integer> pnList = new ArrayList<>();
        for(int i=2;i<=n;i++) {
            if (isPrimeNo(i))
            	pnList.add(Integer.valueOf(i));
        }
        pns.setInitial(n);
        pns.setPnList(pnList);

		return pns;
	}

    public boolean isPrimeNo(int nmbr) {
        for(int i=2;i<=nmbr/2;i++) {
        	if(nmbr%i == 0) return false;
        }
        return true;
	}

	@Override
	public PrimeNumbers calculatePrimeNumbers(int low, int high) {
		// TODO Auto-generated method stub
		return null;
	}
}
