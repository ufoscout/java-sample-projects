/**
 * CalculatorSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.generated.wsAxis.generated;

import it.ufoscout.wsAxis.server.ICalculator;
import it.ufoscout.wsAxis.server.SimpleCalculator;

public class CalculatorSoapBindingImpl implements it.generated.wsAxis.generated.ICalculator{
	
	ICalculator calculator = new SimpleCalculator();
	
    public int add(int in0, int in1) throws java.rmi.RemoteException {
        return calculator.add(in0, in1);
    }

    public int subtract(int in0, int in1) throws java.rmi.RemoteException {
    	return calculator.subtract(in0, in1);
    }

}
