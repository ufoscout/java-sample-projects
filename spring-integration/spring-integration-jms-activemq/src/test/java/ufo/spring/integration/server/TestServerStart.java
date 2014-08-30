package ufo.spring.integration.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ufo.spring.integration.api.Employee;
import ufo.spring.integration.api.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ufo/spring/integration/jms/jms-broker-config.xml", "classpath:ufo/spring/integration/server/server-config.xml" })
public class TestServerStart {

	@Autowired
	private EmployeeService service;

	@Test
	public void retrieveExistingEmployee() throws Exception {

		assertNotNull(service);

		Employee employee = service.retrieveEmployee(2);
		assertNotNull(employee);
		assertEquals(2, employee.getId());
		assertEquals("Dart Fener", employee.getName());

		assertFalse(service.existEmployee("Chewbacca"));
		assertTrue(service.existEmployee("Leia"));

	}
}
