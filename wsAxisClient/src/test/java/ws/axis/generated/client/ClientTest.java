package ws.axis.generated.client;

import java.net.URL;

import junit.framework.TestCase;

import org.apache.axis.message.SOAPHeaderElement;

/*
 * L'esecuzione di questo test richiede che il server sia attivo
 */
public class ClientTest extends TestCase {
	
	private static String simpleCalculatorUrl = "http://localhost:8089/axisServer/services/calculator";
	
    public void testSimpleCalculator1() throws Exception {
        
    	/* 
    	 * METODO 1:
    	 * l'oggetto così creato ha solamente i metodi del ws
    	 */
    	ICalculatorService service = new ICalculatorServiceLocator();
    	ICalculator calc = service.getcalculator(new URL(simpleCalculatorUrl));
        System.out.println("METODO 1:");
        System.out.println("calc.add(15, 6) = " + calc.add(15, 6));
        System.out.println("calc.subtract(15, 6) = " + calc.subtract(15, 6));
        assertEquals( 21 , calc.add(15, 6)  );
        assertEquals( 9 , calc.subtract(15, 6)  );
    }

    public void testSimpleCalculator2() throws Exception {
        /*
         * METODO 2:
         * l'oggetto così creato ha i metodi del ws più tutta una serie di metodi
         * per informazioni aggiuntive sul ws  
         */
    	ICalculatorService service = new ICalculatorServiceLocator();
        CalculatorSoapBindingStub calcStub = new CalculatorSoapBindingStub(new URL(simpleCalculatorUrl), service);
//        calcStub.setPassword(password);
//        calcStub.setUsername(username);
        
        int timeout = 20000;
		calcStub.setTimeout(timeout);
        
        System.out.println(""); 
        System.out.println("METODO 2:");
        System.out.println("calcStub.add(15, 6) = " + calcStub.add(15, 6));
        System.out.println("calcStub.subtract(15, 6) = " + calcStub.subtract(15, 6));
        System.out.println("calcStub.getUsername() = " + calcStub.getUsername());
        System.out.println("calcStub.getPassword() = " + calcStub.getPassword());
        System.out.println("calcStub.getPortName = " + calcStub.getPortName());
        System.out.println("calcStub.getTimeout = " + calcStub.getTimeout());

        assertEquals( timeout , calcStub.getTimeout()  );
        assertEquals( 21 , calcStub.add(15, 6)  );
        assertEquals( 9 , calcStub.subtract(15, 6)  );
        
        SOAPHeaderElement[] soapHeaderElements = calcStub.getHeaders();
        
        for (int i = 0; i < soapHeaderElements.length; i++) {
        	System.out.println("soapHeaderElements[" + i + "].getElementName() = " + soapHeaderElements[i].getElementName());
		}
    }
    
}
