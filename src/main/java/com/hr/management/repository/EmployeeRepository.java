package com.hr.management.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.hr.management.entity.Employee;

@Repository
public class EmployeeRepository {

	@PersistenceContext
	EntityManager entityManager;

	public Employee save(Employee employee) {
		return entityManager.merge(employee);
	}

	@SuppressWarnings("unchecked")
	public Employee getById(String id) {
		try {
			Query<Employee> query = (Query<Employee>) entityManager.createQuery("from Employee e where e.id= :id");
			query.setParameter("id", id);
			query.setMaxResults(1);
			return (Employee) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		Query<Employee> query = (Query<Employee>)entityManager.createQuery("from Employee e where e.isDeleted=0");
		return (List<Employee>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public boolean isEmailAlreadyExists(String email) {
		Query<Employee> query= (Query<Employee>) entityManager.createQuery("SELECT e FROM Employee e WHERE e.email = :email");
		query.setParameter("email", email);
		List<Employee> resultList=query.getResultList();
		return !resultList.isEmpty();
		
	}

	@SuppressWarnings("unchecked")
	public boolean isContactNumberAlreadyExists(String contactNumber) {
		Query<Employee> query= (Query<Employee>) entityManager.createQuery("SELECT e FROM Employee e WHERE e.contactNumber = :contactNumber");
		query.setParameter("contactNumber", contactNumber);
		List<Employee> resultList=query.getResultList();
		return !resultList.isEmpty();
		
	}

}

