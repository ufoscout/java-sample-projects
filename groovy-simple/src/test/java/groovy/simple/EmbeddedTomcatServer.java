package groovy.simple;

import java.io.File;

import org.apache.catalina.startup.Tomcat;



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
public class EmbeddedTomcatServer {

	private static EmbeddedTomcatServer tomcatServer;
	private Tomcat SERVER;
	private final String CONTEXT = "/webtest";
	private final String WEB_APP_DIR = "src/main/webapp";
	private final String TEMP_DIR = "./target/tomcat-temp";
	private final int SERVER_PORT = 0; // serverPort = 0 means
	// "assign arbitrarily port number"
	private int ACTUAL_PORT; // This field is initialized with the real port
	// number used by jetty

	private EmbeddedTomcatServer() throws Exception {
		configureTomcat(SERVER_PORT);
	}

	public static EmbeddedTomcatServer instance() throws Exception {
		if (tomcatServer == null) {
			tomcatServer = new EmbeddedTomcatServer();
		}
		return tomcatServer;
	}

	private void configureTomcat(final int serverPort) throws Exception {

		SERVER = new Tomcat();
		
		SERVER.setBaseDir(TEMP_DIR);
		SERVER.setPort(SERVER_PORT);
		
		String absoluteAppDir = new File("./" + WEB_APP_DIR).getAbsolutePath();
		System.out.println("configuring app with basedir: " + absoluteAppDir);
        SERVER.addWebapp(CONTEXT, absoluteAppDir);
        
        SERVER.start();
        
        ACTUAL_PORT = SERVER.getConnector().getLocalPort();
//        tomcat.getServer().await();  
		System.out.println("Tomcat server started on port: " + ACTUAL_PORT);

	}

	public String getBaseWebUrl() {
		return "http://localhost:" + ACTUAL_PORT + CONTEXT;
	}

}
