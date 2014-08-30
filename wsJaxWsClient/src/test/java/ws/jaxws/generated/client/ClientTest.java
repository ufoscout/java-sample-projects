package ws.jaxws.generated.client;

import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import junit.framework.TestCase;

/*
 * L'esecuzione di questo test richiede che il server sia attivo
 */
public class ClientTest extends TestCase {
	
	// indirizzo remoto del WS	
	private static String _wsURL = "http://localhost:8089/jaxwsServer/service";

	// path locale del wsdl
	private static String _wsdlLocalURL = "file:wsdl/Calculator.wsdl";
//	private static String _wsdlLocalURL = _wsURL + "?wsdl";

	// namespace, e' definito all'inizio del wsdl, ad esempio in questo caso:
	// <definitions targetNamespace="http://ws.server.calculator" ...
	private static String _namespaceUri = "http://ws.server.calculator";

	// nome del servizio, e' definito generalmente in fondo al wsdl, in questo caso:
	// <wsdl:service name="Calculator">
	private static String _localPart = "Calculator";

	private static String _wsUsername = "username";
	private static String _wsPassword = "password";
	private int _wsTimeout = 120000;

	private QName _serviceName;
	private URL _wsdlLocation;
	private static Calculator _service;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		System.out.println("START...");

		System.out.println("wsdlurl: " + _wsURL);
		System.out.println("wsdllocalurl: " + _wsdlLocalURL);
		System.out.println("namespaceUri: " + _namespaceUri);
		System.out.println("localPart: " + _localPart);
		System.out.println("username: " + _wsUsername);
		System.out.println("password: " + _wsPassword);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		System.out.println("...END!");
	}
	
	
	public void testRicercaUtenti() throws Exception {
//		_wsdlLocation = new URL(_wsdlLocalURL);
		_wsdlLocation = getClass().getResource( "/wsdl/Calculator.wsdl" );
		_serviceName = new QName(_namespaceUri, _localPart);
		_service = new Calculator(_wsdlLocation, _serviceName);
		CalculatorPortType stub = _service.getCalculatorPort();
		
		Map<String, Object> requestContext = ((BindingProvider)stub).getRequestContext();
//		requestContext.put(BindingProvider.USERNAME_PROPERTY, _wsUsername);
//		requestContext.put(BindingProvider.PASSWORD_PROPERTY, _wsPassword);
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, _wsURL);
		requestContext.put( "com.sun.xml.ws.connect.timeout", new Integer( _wsTimeout ) );
		requestContext.put( "com.sun.xml.ws.request.timeout", new Integer( _wsTimeout ) );
		
//		requestContext.put( JAXWSProperties.CONNECT_TIMEOUT, new Integer( _wsTimeout ) );
//		requestContext.put( JAXWSProperties.REQUEST_TIMEOUT, new Integer( _wsTimeout ) );
		
        System.out.println("calc.add(15, 6) = " + stub.add(15, 6));
        System.out.println("calc.subtract(15, 6) = " + stub.subtract(15, 6));
        assertEquals( 21 , stub.add(15, 6)  );
        assertEquals( 9 , stub.subtract(15, 6)  );
    }
		
}
