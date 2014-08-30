package rest.client;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * 
 * @author Francesco Cina'
 *
 * 30 May 2012
 */
public abstract class BaseRestTest {

	@Rule
	public TestName name = new TestName();
	private static String BASE_SERVER_URL = "http://localhost:8080/rest/jersey";
	private static Client CLIENT;
	
	@Before
	public void setUpBeforeTest() {
		System.out.println("==================================================================="); //$NON-NLS-1$
		System.out.println("BEGIN TEST " + name.getMethodName()); //$NON-NLS-1$
		System.out.println("==================================================================="); //$NON-NLS-1$
	}

	@After
	public void tearDownAfterTest() {
		System.out.println("==================================================================="); //$NON-NLS-1$
		System.out.println("END TEST " + name.getMethodName()); //$NON-NLS-1$
		System.out.println("==================================================================="); //$NON-NLS-1$
	}
	
	protected WebResource getWebResource() {
		if (CLIENT == null) {
			ClientConfig config = new DefaultClientConfig();
			config.getClasses().add(JacksonJaxbJsonProvider.class);
			CLIENT = Client.create(config);
		}
		WebResource webResource = CLIENT.resource(BASE_SERVER_URL);
		return webResource;
	}
}
