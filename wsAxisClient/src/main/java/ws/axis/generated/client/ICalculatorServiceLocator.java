/**
 * ICalculatorServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.axis.generated.client;

public class ICalculatorServiceLocator extends org.apache.axis.client.Service implements ws.axis.generated.client.ICalculatorService {

    public ICalculatorServiceLocator() {
    }


    public ICalculatorServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ICalculatorServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for calculator
    private java.lang.String calculator_address = "http://localhost:8089/axisServer/services/calculator";

    public java.lang.String getcalculatorAddress() {
        return calculator_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String calculatorWSDDServiceName = "calculator";

    public java.lang.String getcalculatorWSDDServiceName() {
        return calculatorWSDDServiceName;
    }

    public void setcalculatorWSDDServiceName(java.lang.String name) {
        calculatorWSDDServiceName = name;
    }

    public ws.axis.generated.client.ICalculator getcalculator() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(calculator_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getcalculator(endpoint);
    }

    public ws.axis.generated.client.ICalculator getcalculator(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.axis.generated.client.CalculatorSoapBindingStub _stub = new ws.axis.generated.client.CalculatorSoapBindingStub(portAddress, this);
            _stub.setPortName(getcalculatorWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setcalculatorEndpointAddress(java.lang.String address) {
        calculator_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ws.axis.generated.client.ICalculator.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.axis.generated.client.CalculatorSoapBindingStub _stub = new ws.axis.generated.client.CalculatorSoapBindingStub(new java.net.URL(calculator_address), this);
                _stub.setPortName(getcalculatorWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("calculator".equals(inputPortName)) {
            return getcalculator();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("calculatorNamespace", "ICalculatorService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("calculatorNamespace", "calculator"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("calculator".equals(portName)) {
            setcalculatorEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
