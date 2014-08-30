package ufo.jetty9;

import java.io.File;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.ClassInheritanceHandler;
import org.eclipse.jetty.server.NetworkConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.springframework.web.WebApplicationInitializer;

public class EmbeddedJettyServer {

	private Server SERVER;
	private final String CONTEXT = "/";
	private final String TEMP_DIR = "./target/jetty-temp";
	// private final String TLD_DIR = "./target/jetty-temp-tld";
	private final String[] WEB_APP_DIRS = new String[] { "./target/" };
	private final int SERVER_PORT = 0; // serverPort = 0 means "assign arbitrarily port number"
	private int ACTUAL_PORT; // This field is initialized with the real port number used by jetty
	private final Class<? extends WebApplicationInitializer> contextInitializer;

	public EmbeddedJettyServer() {
		this(null);
	}

	public EmbeddedJettyServer(Class<? extends WebApplicationInitializer> contextInitializer) {
		this.contextInitializer = contextInitializer;
		configureJetty(SERVER_PORT);
	}

	private void configureJetty(final int serverPort) {
		try {
			SERVER = new Server(serverPort);

			final WebAppContext webAppContext = new WebAppContext();
			// doTLDWorkAround();

			webAppContext.setContextPath(CONTEXT);
			webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
			// webAppContext.setParentLoaderPriority(true);
			webAppContext.setBaseResource(new ResourceCollection(WEB_APP_DIRS));
			webAppContext.setTempDirectory(new File(TEMP_DIR));

			if (contextInitializer!=null) {
				webAppContext.setConfigurations(new Configuration[] { new WebXmlConfiguration(), new AnnotationConfiguration() {
					@Override
					public void preConfigure(WebAppContext context) throws Exception {
						ConcurrentHashSet<String> set = new ConcurrentHashSet<>();
						set.add(contextInitializer.getName());
						ClassInheritanceMap inhMap = new ClassInheritanceMap();
						inhMap.put(WebApplicationInitializer.class.getName(), set);
						context.setAttribute(CLASS_INHERITANCE_MAP, inhMap);
						_classInheritanceHandler = new ClassInheritanceHandler(inhMap);
					}
				} });
			}
/*
			webAppContext.setConfigurations(new Configuration[] { new WebXmlConfiguration(), new AnnotationConfiguration()});
*/
			SERVER.setHandler(webAppContext);

			SERVER.start();
			ACTUAL_PORT = ((NetworkConnector) SERVER.getConnectors()[0]).getLocalPort();
			System.out.println("Jetty server started on port: " + ACTUAL_PORT);
			// SERVER.join();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public Server getServer() {
		return SERVER;
	}

	public String getBaseWebUrl() {
		return "http://localhost:" + ACTUAL_PORT + CONTEXT;
	}

	/**
	 * Jasper jsp compiler does not find the TLD files from the classpath when
	 * Jetty embedded is launched from command line. I found the same issue in
	 * every version of Jetty and also in Tomcat 7 embedded. The only workaround
	 * I found is to put the TLD files in the WEB-INF folder. This method
	 * extracts all the TLD files from the classpath and put them into a
	 * directory that will be added to the Jetty context at runtime. Please note
	 * that this bug does not appear when Jetty embedded is launched inside
	 * Eclipse (for example in a JUnit test).
	 *
	 * @throws Exception
	 */
	// private void doTLDWorkAround() throws Exception {
	//
	// File outputDir = FileUtil.createDirectoriesTreeIfNotExist(TLD_DIR +
	// "/WEB-INF");
	//
	// for (String tldFileName :
	// ResourceUtil.getClasspathFileNamesWithExtension("tld")) {
	// File outputFile = new File( outputDir , new File(tldFileName).getName()
	// );
	// ResourceUtil.writeEmbeddedResourceToLocalFile(tldFileName, outputFile);
	// }
	//
	// }
}
