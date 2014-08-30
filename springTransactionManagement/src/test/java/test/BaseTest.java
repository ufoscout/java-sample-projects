package test;

import spring.Application;
import junit.framework.TestCase;

public abstract class BaseTest extends TestCase {

	protected Application application;

	protected void setUp() throws Exception {
		super.setUp();
		
		application = new Application();
		assertNotNull( application.getDatasource());
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
