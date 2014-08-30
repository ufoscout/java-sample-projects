package ufo.jetty9;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ufo.jetty9.server.ServerContextInitializer;

public class ServerStartTest extends Jetty9BaseTest {

	@Test
	public void testServerStart() {
		EmbeddedJettyServer jetty = new EmbeddedJettyServer(ServerContextInitializer.class);
		assertNotNull(jetty.getServer());
	}
}
