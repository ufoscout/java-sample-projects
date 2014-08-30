package ufo.gae.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ufo-gae-context.xml" })
public abstract class GaeBaseTest {

	private final LocalServiceTestHelper helper = buildHelper();

	@Rule
	public TestName name = new TestName();

	@Before
	public void setUpBeforeTest() {
		helper.setUp();
		// getLogger().info("===================================================================");
		// getLogger().info("BEGIN TEST " + name.getMethodName());
		// getLogger().info("===================================================================");
	}


	@After
	public void tearDownAfterTest() {
		helper.tearDown();
		// getLogger().info("===================================================================");
		// getLogger().info("END TEST " + name.getMethodName());
		// getLogger().info("===================================================================");
	}

	protected UserService getUserService() {
		return UserServiceFactory.getUserService();
	}

	//In memory DataStore
	private static LocalServiceTestHelper buildHelper() {
		LocalServiceTestHelper newHelper = new LocalServiceTestHelper(
				new LocalUserServiceTestConfig(),
				new LocalDatastoreServiceTestConfig()
				).setEnvIsLoggedIn(true)
				.setEnvAuthDomain("localhost")
				.setEnvEmail("test@localhost");
		return newHelper;
	}

	//This should enable a DataStore on the FileSystem to generate the indexes
	//during the unit tests... but it doesn't work...
	//	private static LocalServiceTestHelper buildHelper() {
	//		File location = new File("target/local_db.bin");
	//
	//		// TODO: Read these elements from the File("war/WEB-INF/appengine-web.xml").
	//		String appId = "ufoscout";
	//		String appVersionId = "1";
	//
	//		LocalDatastoreServiceTestConfig dsConfig = new LocalDatastoreServiceTestConfig()
	//		.setBackingStoreLocation(location.getAbsolutePath())
	//		.setNoStorage(false);
	//		System.out.println(dsConfig.getBackingStoreLocation());
	//
	//		return new LocalServiceTestHelper(new LocalUserServiceTestConfig(), dsConfig)
	//		.setEnvIsAdmin(false)
	//		.setEnvIsLoggedIn(true)
	//		.setEnvAuthDomain("localhost")
	//		.setEnvEmail("test@localhost")
	//		.setEnvAppId(appId)
	//		.setEnvVersionId(appVersionId);
	//	}
}
