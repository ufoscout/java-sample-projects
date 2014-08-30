package test;


import java.io.File;
import java.io.FileInputStream;

import net.sourceforge.jwebunit.junit.WebTestCase;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.resource.ResourceCollection;
import org.mortbay.xml.XmlConfiguration;

/**
 * 
 * @author Francesco Cina'
 *
 * 15/nov/2010
 */
public abstract class BaseWebTestJetty extends WebTestCase {

    
    protected static Server SERVER;
    protected static String CONTEXT = "/context";
    protected static int SERVER_PORT = 0; // serverPort = 0 means "assign arbitrarily port number"
    protected static int ACTUAL_PORT; // This field is initialized with the real port number used by jetty
    

	public BaseWebTestJetty() {
    }

    public BaseWebTestJetty(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        if(SERVER == null) {
        	configureJetty(SERVER_PORT);
        }

        System.out.println("===================================================================");
        System.out.println("INIZIO WEB TEST " + getName());
        System.out.println("===================================================================");

    }



	protected void tearDown() throws Exception {
        super.tearDown();

        System.out.println("===================================================================");
        System.out.println("FINE WEB TEST " + getName());
        System.out.println("===================================================================");
    }
	
	
	private void configureJetty(int serverPort) throws Exception {
        SERVER = new org.mortbay.jetty.Server(serverPort);
        
        XmlConfiguration configuration = new XmlConfiguration(new FileInputStream("src/test/jetty/jetty.xml"));
        configuration.configure(SERVER);
        
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath(CONTEXT);
		webAppContext.setBaseResource(new ResourceCollection("src/main/webapp,src/test/webapp"));
        webAppContext.setTempDirectory( new File("target/work"));
        webAppContext.setDefaultsDescriptor("src/test/jetty/webdefault.xml");

        SERVER.addHandler( webAppContext );
        SERVER.start();

        // getLocalPort returns the port that was actually assigned
        ACTUAL_PORT = SERVER.getConnectors()[0].getLocalPort();
        System.out.println("Jetty server started on port: " + ACTUAL_PORT);

	}
	
	protected String getBaseWebUrl() {
		return "http://localhost:" + ACTUAL_PORT + CONTEXT;		
	}
    
}
