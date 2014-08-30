package ufo.spring.integration.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import ufo.spring.integration.api.Employee;
import ufo.spring.integration.api.EmployeeService;

@MessageEndpoint
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private NodeNameService nameService;

	@Override
	@ServiceActivator(inputChannel="retrieveEmployee")
	public Employee retrieveEmployee(int id) {
		System.out.println("retrievEmployee call received from node " + nameService.getName());
		return employeeRepository.getEmployee(id);
	}

	@Override
	@ServiceActivator(inputChannel="existEmployee")
	public boolean existEmployee(String name) {
		System.out.println("existEmployee call received from node " + nameService.getName());
		return employeeRepository.existEmployee(name);
	}

}
