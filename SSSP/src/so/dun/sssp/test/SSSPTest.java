package so.dun.sssp.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.jpa.QueryHints;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import so.dun.sssp.dao.DepartmentRepository;
import so.dun.sssp.entities.Department;
import so.dun.sssp.entities.Employee;
import so.dun.sssp.service.EmployeeService;

public class SSSPTest {
	
	private EmployeeService employeeService;
	private DepartmentRepository departmentRepository;
	private EntityManagerFactory entityManagerFactory;
	
	private ApplicationContext ctx = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		employeeService = ctx.getBean(EmployeeService.class);
		departmentRepository = ctx.getBean(DepartmentRepository.class);
		entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
		
	}

	@Test
	public void testSataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	
	/**
	 * 用来生成数据库的初始数据
	 */
	@Test
	public void testCrudRepository(){
		List<Employee> employees = new ArrayList<Employee>();
		for(int i = 'a'; i <= 'z'; i++){
			Employee employee = new Employee();
			employee.setLastName((char)i + "" + (char)i);
			employee.setEmail((char)i + "" + (char)i + "@dun.so");
			employee.setBirth(new Date());
			employee.setCreateTime(new Date());
			employees.add(employee);
		}
		employeeService.saveEmployees(employees);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testJpaSecondLevelCache(){
		String jpql = "FROM Department d";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(jpql);
		List<Department> departments = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		System.out.println(departments);
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		query = entityManager.createQuery(jpql);
		departments = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		System.out.println(departments);
		entityManager.close();
	}

	@Test
	public void testRepositorySecondLevelCache(){
		List<Department> departments = departmentRepository.getAll();
		departments = departmentRepository.getAll();
		System.out.println(departments);
	}
}
