package groovy.simple;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;



/**
 * 
 * <class_description>
 * <p><b>notes</b>:
 * <p>ON : Jul 15, 2012
 *
 * @author ARHS Developments - Francesco Cina
 * @version $Revision
 */
@SuppressWarnings("nls")
public class EmbeddedJettyServer {

	private static EmbeddedJettyServer jettyServer;
	private Server SERVER;
	private final String CONTEXT = "/webtest";
	private final int SERVER_PORT = 0; // serverPort = 0 means
	// "assign arbitrarily port number"
	private int ACTUAL_PORT; // This field is initialized with the real port
	// number used by jetty

	private EmbeddedJettyServer() throws Exception {
		configureJetty(SERVER_PORT);
	}

	public static EmbeddedJettyServer instance() throws Exception {
		if (jettyServer == null) {
			jettyServer = new EmbeddedJettyServer();
		}
		return jettyServer;
	}

	private void configureJetty(final int serverPort) throws Exception {
		SERVER = new Server(serverPort);

		final WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath(CONTEXT);
		webAppContext.setBaseResource(new ResourceCollection("src/main/webapp"));
		webAppContext.setTempDirectory(new File("target/jetty-temp"));

		webAppContext.setClassLoader(
	            Thread.currentThread().getContextClassLoader()
	        );

		SERVER.setHandler(webAppContext);
		SERVER.start();

//		SERVER.addHandler(new WebAppContext("src/main/webapp",CONTEXT));
//		SERVER.start();
		ACTUAL_PORT = SERVER.getConnectors()[0].getLocalPort();
		System.out.println("Jetty server started on port: " + ACTUAL_PORT);

	}

	public String getBaseWebUrl() {
		return "http://localhost:" + ACTUAL_PORT + CONTEXT;
	}

}
