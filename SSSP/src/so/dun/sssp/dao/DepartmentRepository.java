package so.dun.sssp.dao;

import java.util.List;

import javax.persistence.QueryHint;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import so.dun.sssp.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	@QueryHints({@QueryHint(name=org.hibernate.jpa.QueryHints.HINT_CACHEABLE,value="true")})
	@Query("FROM Department d")
	List<Department> getAll();
	
	
	
	
	

}
