package com.hr.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.management.entity.LeaveRequest;
import com.hr.management.payload.LeaveRequestDto;
import com.hr.management.payload.LeaveStatus;
import com.hr.management.service.LeaveRequestService;

@RestController
@RequestMapping("/api/leave")
public class LeaveRequestController {

	@Autowired
	LeaveRequestService leaveRequestService;
	
	@PostMapping("{id}/apply")
	public ResponseEntity<?> applyForLeave(@PathVariable("id") String employeeId,@RequestBody LeaveRequestDto leaveRequestDto){
		ResponseEntity<Object> leaveRequest=leaveRequestService.applyForLeave(employeeId, leaveRequestDto);
		return ResponseEntity.ok(leaveRequest.getBody());
		
	}
	
	@GetMapping("/history/{employeeId}")
    public ResponseEntity<?> getLeaveHistory(@PathVariable String employeeId) {
        // Retrieve leave history for a specific employee
        List<LeaveRequest> history = leaveRequestService.getLeaveHistoryForEmployee(employeeId);
        return ResponseEntity.ok(history);
    }
	
	@PutMapping("/{leaveId}/approve")
    public ResponseEntity<?> approveLeaveRequest(@PathVariable String leaveId) {
        ResponseEntity<?> response = leaveRequestService.updateLeaveRequestStatus(leaveId, LeaveStatus.APPROVED);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

	@PutMapping("/{leaveId}/reject")
    public ResponseEntity<?> rejectLeaveRequest(@PathVariable String leaveId) {
        ResponseEntity<?> response = leaveRequestService.updateLeaveRequestStatus(leaveId, LeaveStatus.REJECTED);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
