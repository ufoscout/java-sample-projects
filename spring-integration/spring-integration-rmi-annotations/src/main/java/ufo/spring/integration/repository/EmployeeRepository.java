package ufo.spring.integration.repository;

import ufo.spring.integration.model.Employee;

public interface EmployeeRepository {
	public Employee getEmployee(int id);
	public int employeesCount();
	public boolean existEmployee(String name);
}
