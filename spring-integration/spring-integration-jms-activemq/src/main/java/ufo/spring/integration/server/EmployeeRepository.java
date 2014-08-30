package ufo.spring.integration.server;

import ufo.spring.integration.api.Employee;

public interface EmployeeRepository {
	public Employee getEmployee(int id);
	public int employeesCount();
	public boolean existEmployee(String name);
}
