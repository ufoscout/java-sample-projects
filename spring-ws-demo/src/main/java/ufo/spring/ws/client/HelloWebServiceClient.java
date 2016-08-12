package ufo.spring.ws.client;

import javax.jws.WebService;

import ufo.spring.ws.SpringConfig;

@WebService(
        portName = SpringConfig.HELLO_WEB_SERVICE_PORT_NAME,
        serviceName = SpringConfig.HELLO_WEB_SERVICE_NAME,
        targetNamespace = SpringConfig.HELLO_WEB_SERVICE_NAMESPACE
        //, endpointInterface = "org.superbiz.calculator.ws.CalculatorWs"
        )
public interface HelloWebServiceClient {

	String sayHello(String name);

	String sayHelloWorld();

}
