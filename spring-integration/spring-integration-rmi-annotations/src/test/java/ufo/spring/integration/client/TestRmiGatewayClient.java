package ufo.spring.integration.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ufo.spring.integration.model.Employee;
import ufo.spring.integration.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ufo/spring/integration/test/config/client-gateway-config.xml" })
public class TestRmiGatewayClient {

	@Autowired
	private EmployeeService service;

	@SuppressWarnings("resource")
	@Before
	public void setUp() throws Exception {
		new ClassPathXmlApplicationContext("ufo/spring/integration/config/server-config.xml");
	}

	@Test
	public void retrieveExistingEmployee() throws Exception {

		Employee employee = service.retrieveEmployee(2);
		assertNotNull(employee);
		assertEquals(2, employee.getId());
		assertEquals("Dart Fener", employee.getName());

		assertFalse(service.existEmployee("Chewbacca"));
		assertTrue(service.existEmployee("Leia"));

	}
}
