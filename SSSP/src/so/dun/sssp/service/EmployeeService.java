package so.dun.sssp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import so.dun.sssp.dao.EmployeeCrudRepsotory;
import so.dun.sssp.dao.EmployeeRepository;
import so.dun.sssp.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeCrudRepsotory employeeCrudRepsotory;
	
	@Transactional(readOnly=true)
	public Page<Employee> getPage(int pageNo,int pageSize) {
		PageRequest pageRequest = new PageRequest(pageNo-1, pageSize);
		return employeeRepository.findAll(pageRequest);
	}
	
	@Transactional
	public void saveEmployees(List<Employee> employees) {
		employeeCrudRepsotory.save(employees);
	}
	
	@Transactional(readOnly=true)
	public Employee getByLastName(String lastName){
		return employeeRepository.getByLastName(lastName);
	}
	
	@Transactional
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setCreateTime(new Date());
		}
		employeeRepository.saveAndFlush(employee);
	}
	
	@Transactional(readOnly=true)
	public Employee get(Integer id){
		return employeeRepository.findOne(id); 
	}
	
	@Transactional
	public void delete(Integer id){
		employeeRepository.delete(id);
	}
}
