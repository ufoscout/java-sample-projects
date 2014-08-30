package ufo.spring.integration.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ufo.spring.integration.model.Employee;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	private final Map<Integer, Employee> employees = new HashMap<>();

	public EmployeeRepositoryImpl() {
		employees.put(0, new Employee(0, "Leia"));
		employees.put(1, new Employee(1, "Luke Skywalker"));
		employees.put(2, new Employee(2, "Dart Fener"));
		employees.put(3, new Employee(3, "R2D2"));
	}

	@Override
	public Employee getEmployee(int id) {
		return employees.get(id);
	}

	@Override
	public int employeesCount() {
		return employees.size();
	}

}
