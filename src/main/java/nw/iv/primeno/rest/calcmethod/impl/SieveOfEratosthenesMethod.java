package nw.iv.primeno.rest.calcmethod.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.entity.PrimeNumbers;

@Service
public class SieveOfEratosthenesMethod implements CalculationMethod {
	
	private int[] primeNos = new int[10000001];
	
	@Override
	@Cacheable(value = "primenumbers", key = "#n")
	public PrimeNumbers calculatePrimeNumbers(int n) {
		PrimeNumbers pns = new PrimeNumbers();
		List<Integer> pnList = new ArrayList<>();
		SieveOfEratosthenes(n);
		for (int i=1;i<=n;i++) {
			if (i == 2)
				pnList.add(Integer.valueOf(i));
			else if (i%2 == 1 && primeNos[i/2] == 0)
				pnList.add(Integer.valueOf(i));
		}
        pns.setPnList(pnList);
        pns.setN(n);
		
		return pns;
	}
	
	private void SieveOfEratosthenes(int n)
	{
		primeNos[0]=1;
		for (int i=3;i*i<=n;i+=2) {
			if (primeNos[i/2] == 0) {
				for (int j=3*i;j<=n;j+=2*i)
					primeNos[j/2] = 1;
			}
		}
	}

	@Override
	public PrimeNumbers calculatePrimeNumbers(int low, int high) {
		// TODO Auto-generated method stub
		return null;
	}
}
