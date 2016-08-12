package test.socket.client;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;
import org.junit.Test;

import test.socket.SocketApplicationTests;
import test.socket.server.Server;

public class ClientTest extends SocketApplicationTests {

	@Test
	public void client_should_be_created_if_server_not_available() {
		int port = getFreePort(5000);
		System.out.println("Found free port: " + port);

		Client client = Client.build("localhost", port);
		assertNotNull(client);
	}

	@Test
	public void client_should_be_created_if_server_available() {
		int port = getFreePort(5000);
		System.out.println("Found free port: " + port);

		Server server = Server.build(port, false);
		assertNotNull(server);

		Client client = Client.build("localhost", port);
		assertNotNull(client);

		server.close();
	}

	@Test
	public void client_should_send_a_message() {
		int port = getFreePort(5000);
		System.out.println("Found free port: " + port);

		Server server = Server.build(port, false);
		assertNotNull(server);

		Client client = Client.build("localhost", port);
		assertNotNull(client);

		server.start();

		String eventName = "event-" + UUID.randomUUID().toString();

		server.consume(eventName, (String t) -> {
			return t+t;
		});

		assertEquals("HelloWorld" + "HelloWorld", client.send(eventName, "HelloWorld"));

		String outputMessage = "Hello World! - " + new Date();
		assertEquals(outputMessage + outputMessage, client.send(eventName, outputMessage));

		assertNull(client.send(eventName + "-notExisting", ""));

		server.close();
	}
}
