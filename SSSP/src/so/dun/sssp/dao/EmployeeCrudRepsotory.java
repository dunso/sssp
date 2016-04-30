package so.dun.sssp.dao;

import org.springframework.data.repository.CrudRepository;

import so.dun.sssp.entities.Employee;

public interface EmployeeCrudRepsotory extends CrudRepository<Employee, Integer>{

}
