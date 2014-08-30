package ufo.spring.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufo.spring.integration.model.Employee;
import ufo.spring.integration.repository.EmployeeRepository;

@Service("defaultEmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee retrieveEmployee(int id) {
		return employeeRepository.getEmployee(id);
	}

}
