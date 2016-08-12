package ufo.spring.ws;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

import ufo.spring.ws.client.HelloWebServiceClient;
import ufo.spring.ws.server.HelloWebService;

@Configuration
public class SpringConfig {

	public static final String HELLO_WEB_SERVICE_NAME = "HelloService";
	public static final String HELLO_WEB_SERVICE_PORT_NAME = HELLO_WEB_SERVICE_NAME + "Port";
	public static final String HELLO_WEB_SERVICE_NAMESPACE = "http://example/";
	public static final String WS_ADDRESS = "http://localhost:8081/";

	@Bean
	public SimpleJaxWsServiceExporter simpleJaxWsServiceExporter() {
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
		exporter.setBaseAddress(WS_ADDRESS);
		return exporter;
	}

	@Bean
	public HelloWebService helloWebService() {
		return new HelloWebService();
	}

	@Bean
	public JaxWsPortProxyFactoryBean helloWebServiceClient() throws MalformedURLException {
		JaxWsPortProxyFactoryBean factory = new JaxWsPortProxyFactoryBean();

		factory.setServiceInterface(HelloWebServiceClient.class);
		factory.setWsdlDocumentUrl(new URL(WS_ADDRESS + HELLO_WEB_SERVICE_NAME + "Endpoint?WSDL"));
//		factory.setNamespaceUri(HELLO_WEB_SERVICE_NAMESPACE);
//		factory.setServiceName(HELLO_WEB_SERVICE_NAME);
//		factory.setPortName(HELLO_WEB_SERVICE_PORT_NAME);

		return factory;
	}


}
