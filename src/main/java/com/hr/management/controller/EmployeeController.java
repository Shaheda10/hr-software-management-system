package com.hr.management.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.management.entity.Employee;
import com.hr.management.payload.EmployeePayload;
import com.hr.management.service.EmployeeService;
import com.hr.management.util.PdfGenerator;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	private PdfGenerator pdfGenerator;

	@PostMapping("/create")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeePayload employeePayload) {
		System.out.println("Inside Controller");
		return employeeService.createEmployee(employeePayload);

	}

	@PutMapping("/{id}/update")
	public ResponseEntity<Object> updateEmployee(@PathVariable String id, @RequestBody EmployeePayload employeePayload) {
		return employeeService.updateEmployee(id, employeePayload);

	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		return employeeService.getEmployees();

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable String id) {
		return employeeService.getEmployeeById(id);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable String id) {
		return employeeService.deleteEmployee(id);

	}
	
	@PostMapping("/generate")
	public ResponseEntity<Object> generateSalarySlip(@RequestParam String employeeId) throws MalformedURLException, IOException {
	    ResponseEntity<Object> response = employeeService.generateSalarySlipPdf(employeeId);

	    // Extracting the download link or file path from the response
	    if (response.getStatusCode() == HttpStatus.OK) {
	        String downloadLink = "/Users/cipher/Documents/SalarySlip/salary_slip.pdf"; // Modify this with the actual link or file path from the response
	        return ResponseEntity.ok("Salary Slip generated... Download link: " + downloadLink);
	    } else {
	        // Handle any error cases here
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Error generating salary slip.");
	    }
	}
	
	@PostMapping("/generate-reference-letter")
	public ResponseEntity<Object> generateEmployeeReferenceLetter(@RequestParam String employeeId) throws MalformedURLException, IOException {
		ResponseEntity<Object> response = employeeService.generateReferenceLetter(employeeId);

	    // Extracting the download link or file path from the response
	    if (response.getStatusCode() == HttpStatus.OK) {
	        String downloadLink = "/Users/cipher/Documents/Empl-ReferenceLetter/employee_reference_letter.pdf"; 
	        return ResponseEntity.ok("Employee Reference Letter generated. Download link: " + downloadLink);
	    } else {
	        // Handle any error cases here
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Error generating Reference Letter.");
	    }

	}



}
