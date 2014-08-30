package ufo.spring.integration.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ufo.spring.integration.api.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ufo/spring/integration/client/client-config.xml" })
public class TestClientStart {

	@Autowired
	private EmployeeServiceGateway service;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Starting the broker
		new ClassPathXmlApplicationContext("classpath:ufo/spring/integration/jms/jms-broker-config.xml");
		//Starting the applications that listen for the specific service
		new ClassPathXmlApplicationContext("classpath:ufo/spring/integration/server/server-config.xml");
		new ClassPathXmlApplicationContext("classpath:ufo/spring/integration/server/server-config.xml");
	}

	@Test
	public void retrieveExistingEmployee() throws Exception {

		assertNotNull(service);
		int howManyCalls = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(howManyCalls);

		for (int i=0; i<howManyCalls; i++) {
			new Thread( new Runnable() {
				@Override
				public void run() {
					Employee employee = service.retrieveEmployee(2);

					assertNotNull(employee);
					assertEquals(2, employee.getId());
					assertEquals("Dart Fener", employee.getName());

					assertFalse(service.existEmployee("Chewbacca"));
					assertTrue(service.existEmployee("Leia"));

					employee = service.retrieveEmployee(2);
					countDownLatch.countDown();
				}
			}).start();
		}

		countDownLatch.await(30, TimeUnit.SECONDS);
		assertEquals(0l, countDownLatch.getCount());
	}


	@Test
	public void retrieveExistingEmployeeAsynch() throws Exception {

		assertNotNull(service);
		Employee employee = service.retrieveEmployeeAsynch(2).get(5, TimeUnit.SECONDS);
		assertNotNull(employee);

	}
}
