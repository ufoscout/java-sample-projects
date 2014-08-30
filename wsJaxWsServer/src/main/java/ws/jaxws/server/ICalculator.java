package ws.jaxws.server;

import javax.jws.WebService;

@WebService (name="CalculatorPortType", targetNamespace = "http://ws.server.calculator")
public interface ICalculator {
    
	int add (int x, int y);
    
	int subtract(int x, int y);
	
}