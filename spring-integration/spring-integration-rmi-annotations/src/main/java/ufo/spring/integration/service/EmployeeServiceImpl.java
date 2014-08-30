package ufo.spring.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import ufo.spring.integration.model.Employee;
import ufo.spring.integration.repository.EmployeeRepository;

@MessageEndpoint
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private NodeNameService nameService;

	@Override
	@ServiceActivator(inputChannel="requestEmployee")
	public Employee retrieveEmployee(int id) {
		System.out.println("retrievEmployee call received from node " + nameService.getName());
		return employeeRepository.getEmployee(id);
	}

	@Override
	@ServiceActivator(inputChannel="requestEmployee")
	public boolean existEmployee(String name) {
		System.out.println("existEmployee call received from node " + nameService.getName());
		return employeeRepository.existEmployee(name);
	}

}
