package com.hr.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.management.entity.Employee;
import com.hr.management.entity.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, String> {
	List<LeaveRequest> findByEmployee(Employee employee);

}
