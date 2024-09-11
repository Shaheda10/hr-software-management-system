package com.hr.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.management.entity.Employee;
import com.hr.management.entity.LeaveRequest;
import com.hr.management.payload.LeaveRequestDto;
import com.hr.management.payload.LeaveStatus;
import com.hr.management.payload.LeaveType;
import com.hr.management.repository.LeaveRequestRepository;
import com.hr.management.util.Constants;

@Service
public class LeaveRequestService {

	@Autowired
	LeaveRequestRepository leaveRequestRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	
	public ResponseEntity<Object> applyForLeave(String employeeId, LeaveRequestDto leaveRequestDto) {
		ResponseEntity<Object> employeeResponse = employeeService.getEmployeeById(employeeId);

        if (employeeResponse.getStatusCode().is2xxSuccessful()) {
            Employee employee = (Employee) employeeResponse.getBody();
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setEmployee(employee);
            leaveRequest.setId(Constants.EMPL_PREFIX.concat(UUID.randomUUID().toString()));
            leaveRequest.setLeaveType(LeaveType.VACATION); 
            leaveRequest.setStatus(LeaveStatus.PENDING);
            leaveRequest.setStartDate(leaveRequestDto.getStartDate());
            leaveRequest.setEndDate(leaveRequestDto.getEndDate());
            return ResponseEntity.ok(leaveRequestRepository.save(leaveRequest));
        } else {
            return ResponseEntity.status(employeeResponse.getStatusCode()).body(employeeResponse.getBody());
        }
       
    }
	
	@SuppressWarnings("unchecked")
	public List<LeaveRequest> getLeaveHistoryForEmployee(String employeeId) {
        // Retrieve leave history for a specific employee
       ResponseEntity<Object>  employeeResponse = employeeService.getEmployeeById(employeeId);
       if (employeeResponse.getStatusCode().is2xxSuccessful()) {
           Employee employee = (Employee) employeeResponse.getBody();
        return leaveRequestRepository.findByEmployee(employee);
    }else {
        // Handle not found or other errors here
        return (List<LeaveRequest>) ResponseEntity.status(employeeResponse.getStatusCode()).body(employeeResponse.getBody());
    }
	}

	 public ResponseEntity<Object> updateLeaveRequestStatus(String leaveId, LeaveStatus status) {
	        Optional<LeaveRequest> leaveRequestOptional = leaveRequestRepository.findById(leaveId);

	        if (leaveRequestOptional.isPresent()) {
	            LeaveRequest leaveRequest = leaveRequestOptional.get();
	            leaveRequest.setStatus(status);
	            leaveRequestRepository.save(leaveRequest);
	            return ResponseEntity.ok("Leave request updated successfully");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	

}

