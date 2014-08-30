package ufo.spring.integration.api;


public interface EmployeeService {

	public Employee retrieveEmployee(int id);

	public boolean existEmployee(String name);

}
