package ufo.spring.integration.client;

import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;

import ufo.spring.integration.api.Employee;

public interface EmployeeServiceGateway {

	@Gateway(requestChannel="retrieveEmployee")
	public Employee retrieveEmployee(int id);

	@Gateway(requestChannel="retrieveEmployee")
	public Future<Employee> retrieveEmployeeAsynch(int id);

	@Gateway(requestChannel="existEmployee")
	public boolean existEmployee(String name);

}
