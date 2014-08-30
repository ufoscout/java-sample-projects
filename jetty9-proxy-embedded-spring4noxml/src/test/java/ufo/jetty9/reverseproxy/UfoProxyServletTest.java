package ufo.jetty9.reverseproxy;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import ufo.jetty9.EmbeddedJettyServer;
import ufo.jetty9.server.ServerContextInitializer;

public class UfoProxyServletTest {

	@Test
	public void test() throws InterruptedException {
		//Start Remote server
		EmbeddedJettyServer remoteServer = new EmbeddedJettyServer(ServerContextInitializer.class);
		assertNotNull(remoteServer.getServer());

		//Test direct call to the server:
		RestTemplate template = new RestTemplate();
		//remoteServer.getServer().join();
		assertEquals("Hello World!", template.getForObject(remoteServer.getBaseWebUrl() + "/rest/welcome/sayHelloWorld", String.class));

		StaticUglyProxyConfig.remoteUrl = remoteServer.getBaseWebUrl();

		//remoteServer.getServer().join();

		//Start proxy server
		EmbeddedJettyServer proxyServer = new EmbeddedJettyServer(ProxyContextInitializer.class);
		assertNotNull(proxyServer.getServer());

		assertEquals("Hello World!", template.getForObject(proxyServer.getBaseWebUrl() + "/proxy/welcome/sayHelloWorld", String.class));

	}

}
