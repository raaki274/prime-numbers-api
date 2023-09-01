package nw.iv.primeno.rest.calcmethod.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static java.lang.Math.sqrt;
import static java.lang.Math.floor;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.entity.PrimeNumbers;

@Service
public class SegmentedSieveMethod implements CalculationMethod {
	
	@Override
	@Cacheable(value = "primenumbers", key = "#n")
	public PrimeNumbers calculatePrimeNumbers(int n) {
		PrimeNumbers pns = new PrimeNumbers();
		List<Integer> pnList = new ArrayList<>();
		
        int limit = (int) (floor(sqrt(n))+1);
        Vector<Integer> prime = new Vector<>();
        
        simpleSieve(limit, prime, pnList);
      
        int low  = limit;
        int high = 2*limit;
      
        while (low < n)
        {
            if (high >= n)
                high = n;
 
            boolean mark[] = new boolean[limit+1];
             
            for (int i = 0; i < mark.length; i++)
                mark[i] = true;
      
            for (int i = 0; i < prime.size(); i++)
            {
                int loLim = (int) (floor(low/prime.get(i)) * prime.get(i));
                if (loLim < low)
                    loLim += prime.get(i);
      
                for (int j=loLim; j<high; j+=prime.get(i))
                    mark[(int) (j-low)] = false;
            }
      
            for (int i = low; i<high; i++)
                if (mark[(int) (i - low)] == true) {
                	pnList.add(Integer.valueOf(i));
                }
            
            low  = low + limit;
            high = high + limit;
        }
        pns.setPnList(pnList);
        pns.setN(n);
			    
		return pns;
	}
	
	private void simpleSieve(int limit, Vector<Integer> prime, List<Integer> pnList)
    {
        boolean mark[] = new boolean[(limit+1)];
         
        for (int i = 0; i < mark.length; i++)
            mark[i] = true;
      
        for (int p=2; p*p<limit; p++)
        {
            if (mark[p] == true)
            {
                for (int i=(p*p); i<limit; i+=p)
                    mark[i] = false;
            }
        }
        for (int p=2; p<limit; p++)
        {
            if (mark[p] == true)
            {
                prime.add(p);
                pnList.add(Integer.valueOf(p));
            }
        }
    }
	
	@Async
	@Override
	public PrimeNumbers calculatePrimeNumbers(int low, int high)
    {
		System.out.println("inside calc prime for a range: "+low);
		PrimeNumbers pns = new PrimeNumbers();
		List<Integer> pnList = new ArrayList<>();
		
		ArrayList<Integer> chprime= new ArrayList<Integer>();
        fillPrime(chprime,high);
        boolean[] prime=new boolean [high-low+1];
        Arrays.fill(prime,true);   
        
        for(int i:chprime) {
	        int lower=(low/i);
	        if(lower<=1) {
	        	lower=i+i;
	        }
	        else if(low%i!=0) {
	        	lower=(lower*i)+i;
	        } else{
	            lower=(lower*i);
	        }
	        
	        for(int j=lower;j<=high;j=j+i)
	        {
	        	prime[j-low]=false;
	        }
        }
        
        for(int i=low;i<=high;i++) {
            if(prime[i-low]==true) {
            	pnList.add(Integer.valueOf(i));
            }
        }
        
        pns.setPnList(pnList);
        pns.setN(low);
        
        return pns;
    }
    
	public static void fillPrime(ArrayList<Integer> chprime, int high)
    {
        boolean[] ck = new boolean[high+1];
        Arrays.fill(ck,true);
        ck[1]=false;
        ck[0]=false;
     
	    for(int i=2;(i*i)<=high;i++)
	    {
	        if(ck[i]==true)
	        {
	            for(int j=i*i;j<=Math.sqrt(high);j=j+i)
	            {
	                ck[j]=false;
	            }
	        }
	    }
	    for(int i=2;i*i<=high;i++)
	    {
	        if(ck[i]==true)
	        {
	           chprime.add(i);
	        }
	    }
    }

}
