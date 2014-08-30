package jms.ufo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ARHS Developments Francesco Cina' - 29/giu/2011
 * @version $Revision
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:jms-context.xml" })
public abstract class BaseTest {

	@Rule
	public TestName name = new TestName();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@BeforeClass
	public static void setUpBeforeClass() {
		// do nothing
	}

	@AfterClass
	public static void tearDownAfterClass() {
		// Do nothing
	}

	@Before
	public void setUpBeforeTest() {

		getLogger().info("==================================================================="); //$NON-NLS-1$
		getLogger().info("BEGIN TEST " + name.getMethodName()); //$NON-NLS-1$
		getLogger().info("==================================================================="); //$NON-NLS-1$

	}

	@After
	public void tearDownAfterTest() {

		getLogger().info("==================================================================="); //$NON-NLS-1$
		getLogger().info("END TEST " + name.getMethodName()); //$NON-NLS-1$
		// System.out.println("execution time: " + time + " seconds");
		getLogger().info("==================================================================="); //$NON-NLS-1$
	}

	public Logger getLogger() {
		return logger;
	}

}
