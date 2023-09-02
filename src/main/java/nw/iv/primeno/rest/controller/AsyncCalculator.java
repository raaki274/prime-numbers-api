package nw.iv.primeno.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.calcmethod.impl.CalculationMethodFactory;
import nw.iv.primeno.rest.entity.PrimeNumbers;

@EnableAsync
@Component
public class AsyncCalculator {
	
	@Autowired
	private CalculationMethodFactory cf;
	
	@Autowired
	private Environment env;
	
	@Cacheable(value = "primenumbers", key = "#n")
	public PrimeNumbers asyncCalculator(int n) throws InterruptedException {
		
		int sliceSize = Integer.parseInt(env.getProperty("MAX_SLICE_NUMBER"));
		int iter = n/sliceSize;
		int reminder = n%sliceSize;
		int low = 1;
		int high = sliceSize;
		
		CalculationMethod calcMethod = cf.getCalcMethod(CalcMethods.segsie);
		SortedMap<Integer, List<Integer>> cMap = new TreeMap<>();
		PrimeNumbers pns;
		for(int i=0; i<iter; i++) {
			pns = calcMethod.calculatePrimeNumbers(low+1, high);
			cMap.put(Integer.valueOf(low), pns.getPnList());
			low=high;
			high=high+sliceSize;
		}
		if(reminder > 0) {
			pns = calcMethod.calculatePrimeNumbers(high+1, high+reminder);
			cMap.put(Integer.valueOf(high), pns.getPnList());
		}
		
		List<Integer> pnList = new ArrayList<>();
		cMap.forEach((k, v) -> {
			pnList.addAll(v);
		});
		
		pns = new PrimeNumbers();
		pns.setInitial(n);
		pns.setPnList(pnList);
		
		return pns;
	}
		
}
