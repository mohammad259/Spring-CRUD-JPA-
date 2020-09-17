package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//Add fields for EntityManager
	private EntityManager entityManager;
	
	//Set Up for Constructor Injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	
	@Override
	
	public List<Employee> findAll() {

		//get the current HIBERNATE session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> theQuery=
							currentSession.createQuery("from Employee",Employee.class);
		
		//execute query and get return list 
		List<Employee> employees = theQuery.getResultList();
		
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get the current HIBERNATE session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//Get the employee
		Employee theEmployee=currentSession.get(Employee.class, theId);
		
		//return the employee
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//get the current HIBERNATE Session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//Save employee
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		//get the current HIBERNATE Session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//Delete object using primary key
		Query theQuery = 
						currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
	}

}
