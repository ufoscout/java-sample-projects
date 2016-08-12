package ufo.spring.ws.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ufo.spring.ws.SpringConfig;

@WebService(
        portName = SpringConfig.HELLO_WEB_SERVICE_PORT_NAME,
        serviceName = SpringConfig.HELLO_WEB_SERVICE_NAME,
        targetNamespace = SpringConfig.HELLO_WEB_SERVICE_NAMESPACE
        //, endpointInterface = "org.superbiz.calculator.ws.CalculatorWs"
        )
public class HelloWebService {

    @WebMethod
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }

    @WebMethod
    public String sayHelloWorld() {
        return sayHello("world");
    }

}