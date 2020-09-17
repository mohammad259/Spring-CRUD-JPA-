package com.luv2code.springboot.cruddemo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.luv2code.springboot.cruddemo.entity.Employee;


@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;
	
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		//create the Query
		Query theQuery=entityManager.createQuery("from Employee");
		//execute the query and get result list 
		List<Employee> employees = theQuery.getResultList();
		//return the result list
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get the Employee
		Employee theEmployee=entityManager.find(Employee.class, theId);
		//return the employee
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//save or update the Employee
		Employee dbEmployee=entityManager.merge(theEmployee);
		
		//update with id from database ... so we can get generated id for save/insert
		theEmployee.setId(dbEmployee.getId());
		
	}

	@Override
	public void deleteById(int theId) {

		//delete object with primary key
		Query theQuery=entityManager.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}




