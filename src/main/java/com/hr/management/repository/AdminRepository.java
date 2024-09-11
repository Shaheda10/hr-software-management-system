package com.hr.management.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hr.management.entity.Admin;

@Repository
public class AdminRepository {

	@PersistenceContext
	EntityManager entityManager;

	public Admin save(Admin admin) {
		return entityManager.merge(admin);
	}

	public boolean isEmailExists(String email) {
		@SuppressWarnings("unchecked")
		Query<Admin> query = (Query<Admin>) entityManager.createQuery("SELECT a FROM Admin a WHERE a.email = :email");
		query.setParameter("email", email);

		List<Admin> resultList = query.getResultList();
		return !resultList.isEmpty();
	}

	public boolean isContactNumberExists(String contactNumber) {
		@SuppressWarnings("unchecked")
		Query<Admin> query = (Query<Admin>) entityManager
				.createQuery("SELECT a FROM Admin a WHERE a.contactNumber = :contactNumber");
		query.setParameter("contactNumber", contactNumber);

		List<Admin> resultList = query.getResultList();
		return !resultList.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public List<Admin> getAdmins() {
		Query query = (Query) entityManager.createQuery("from Admin a where a.isDeleted= 0");

		return (List<Admin>) query.getResultList();

	}

	public Admin getById(String id) {
		try {
			Query query = (Query) entityManager.createQuery("from Admin a where a.id = :id");
			query.setParameter("id", id);
			query.setMaxResults(1); // Ensures only one result is fetched, if found
			return (Admin) query.getSingleResult();
		} catch (NoResultException e) {
			return null; // Return null if no result found
		}

	}

	public Admin findByUsername(String username) {
		try {
			Query query = (Query) entityManager.createQuery("from Admin a where a.username = :username");
			query.setParameter("username", username);
			query.setMaxResults(1); // Ensures only one result is fetched, if found
			return (Admin) query.getSingleResult();
		} catch (NoResultException e) {
			return null; // Return null if no result found
		}
	}

	
}
