package ufo.spring.integration.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ufo.spring.integration.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ufo/spring/integration/test/config/client-config.xml"})
public class TestRmiClient {

	@Autowired
	MessageChannel localChannel;

	@Autowired
	MessagingTemplate template;

	@SuppressWarnings("resource")
	@Before
	public void setUp() throws Exception {
		new ClassPathXmlApplicationContext("ufo/spring/integration/config/server-config.xml");
	}

	@Test
	public void retrieveExistingEmployee() {
		Employee employee = template.convertSendAndReceive(localChannel, 2, Employee.class);

		assertNotNull(employee);
		assertEquals(2, employee.getId());
		assertEquals("Dart Fener", employee.getName());
	}
}
