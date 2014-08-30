package ufo.jetty9;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ufo.jetty9.config.web.ContextInitializer;

public class ServerStartTest extends Jetty9BaseTest {

	@Test
	public void testServerStart() {
		EmbeddedJettyServer jetty = new EmbeddedJettyServer(ContextInitializer.class);
		assertNotNull(jetty.getServer());
	}
}
