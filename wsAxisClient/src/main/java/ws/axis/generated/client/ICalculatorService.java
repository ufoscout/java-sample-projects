/**
 * ICalculatorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.axis.generated.client;

public interface ICalculatorService extends javax.xml.rpc.Service {
    public java.lang.String getcalculatorAddress();

    public ws.axis.generated.client.ICalculator getcalculator() throws javax.xml.rpc.ServiceException;

    public ws.axis.generated.client.ICalculator getcalculator(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
