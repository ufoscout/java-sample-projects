package test;

import net.sourceforge.jwebunit.junit.WebTestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 22/nov/2010
 */
public class HelloWorldRestTest extends WebTestCase {

	private EmbeddedJettyServer server;

	protected void setUp() throws Exception {
		super.setUp();
		
		server = EmbeddedJettyServer.instance();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testHelloWorld() throws Exception {
		setBaseUrl( server.getBaseWebUrl() );
		beginAt("/rest/helloworld");
		
		System.out.println("sleep...");
		Thread.sleep(500);
		System.out.println("response: " + getPageSource() );
		assertEquals( "Hello World!" , getPageSource() );
	}

}
