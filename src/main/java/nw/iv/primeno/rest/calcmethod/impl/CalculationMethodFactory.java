package nw.iv.primeno.rest.calcmethod.impl;

import org.springframework.stereotype.Component;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.controller.CalcMethods;

@Component
public class CalculationMethodFactory {
    public CalculationMethod getCalcMethod(CalcMethods method)
    {
        if (method == null)
            return new NaiveMethod();
        switch (method) {
        case siera:
            return new SieveOfEratosthenesMethod();
        case segsie:
            return new SegmentedSieveMethod();
        default:
            throw new IllegalArgumentException("Unknown method "+method.name());
        }
    }
}
