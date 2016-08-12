package test.socket.server;

import static org.junit.Assert.*;

import org.junit.Test;

import test.socket.SocketApplicationTests;

public class ServerTest extends SocketApplicationTests {

	@Test
	public void socket_should_be_instantiated() {
		int port = getFreePort(5000);
		System.out.println("Found free port: " + port);
		Server server = Server.build(port, false);
		assertNotNull(server);
		assertEquals(port, server.getPort());

		assertFalse( isPortFree(server.getPort()) );
		server.close();
		assertTrue( isPortFree(server.getPort()) );

	}

	@Test(expected=RuntimeException.class)
	public void socket_should_not_be_instantiated_on_busy_port() {
		int port = getNotFreePort(5000);
		assertFalse( isPortFree(port) );
		System.out.println("Found not free port: " + port);
		Server.build(port, false);
	}

	@Test
	public void socket_should_use_the_first_free_port() {
		int notFreePort = getNotFreePort(5000);
		int freePort = getFreePort(notFreePort);
		assertFalse( isPortFree(notFreePort) );
		assertTrue( isPortFree(freePort) );
		System.out.println("Found free port: " + freePort + " - not free port: " + notFreePort);

		Server server = Server.build(notFreePort, true);
		assertNotNull(server);
		assertEquals(freePort, server.getPort());

		assertFalse( isPortFree(server.getPort()) );
		server.close();
		assertTrue( isPortFree(server.getPort()) );
	}

}
