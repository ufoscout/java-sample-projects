package ufo.spring.ws.client;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ufo.spring.ws.BaseTest;

public class HelloWebServiceClientTest extends BaseTest {

	@Autowired
	private HelloWebServiceClient client;

	@Test
	public void testClient() {
		assertEquals("Hello, world!", client.sayHelloWorld());

		String name = UUID.randomUUID().toString();

		assertEquals("Hello, " + name + "!", client.sayHello(name));
	}

}
