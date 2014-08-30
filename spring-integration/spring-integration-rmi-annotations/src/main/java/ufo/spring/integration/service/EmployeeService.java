package ufo.spring.integration.service;

import ufo.spring.integration.model.Employee;

public interface EmployeeService {

	public Employee retrieveEmployee(int id);

	public boolean existEmployee(String name);

}
