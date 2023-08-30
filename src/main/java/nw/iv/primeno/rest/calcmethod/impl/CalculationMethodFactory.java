package nw.iv.primeno.rest.calcmethod.impl;

import nw.iv.primeno.rest.calcmethod.CalculationMethod;
import nw.iv.primeno.rest.controller.CalcMethods;

public class CalculationMethodFactory {
    public CalculationMethod getCalcMethod(CalcMethods method)
    {
        if (method == null)
            return new NaiveMethod();
        switch (method) {
        case siera:
            return new SieveOfEratosthenesMethod();
        case siesun:
            return new SieveOfSundaramMethod();
        default:
            throw new IllegalArgumentException("Unknown method "+method.name());
        }
    }
}
