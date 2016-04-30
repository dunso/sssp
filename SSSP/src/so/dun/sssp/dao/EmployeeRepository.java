package so.dun.sssp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import so.dun.sssp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee getByLastName(String lastName);
}
