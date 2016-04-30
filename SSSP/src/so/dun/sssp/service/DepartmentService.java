package so.dun.sssp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import so.dun.sssp.dao.DepartmentRepository;
import so.dun.sssp.entities.Department;

@Service
public class DepartmentService {

	@Autowired
	public DepartmentRepository departmentRepository;
	
	@Transactional(readOnly=true)
	public List<Department> getAll(){
		return departmentRepository.getAll();
	}
}
