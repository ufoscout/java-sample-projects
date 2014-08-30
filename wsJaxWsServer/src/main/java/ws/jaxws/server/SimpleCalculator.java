package ws.jaxws.server;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService(serviceName = "Calculator",
        portName="CalculatorPort",
        endpointInterface = "ws.jaxws.server.ICalculator",
        targetNamespace = "http://ws.server.calculator",
        wsdlLocation = "WEB-INF/wsdl/Calculator.wsdl")
        
public class SimpleCalculator implements ICalculator {

    @Resource
    private WebServiceContext context;

	
	public int add(int a, int b) {
		System.out.println("User Principal: " + context.getUserPrincipal());
		return a + b;
	}

	public int subtract(int a, int b) {
		System.out.println("User Principal: " + context.getUserPrincipal());
		return a - b;
	}

	public int multiply(int a, int b) {
		System.out.println("User Principal: " + context.getUserPrincipal());
		return a * b;
	}
}