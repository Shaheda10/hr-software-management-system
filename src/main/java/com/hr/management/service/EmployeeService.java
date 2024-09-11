package com.hr.management.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.management.entity.Employee;
import com.hr.management.payload.EmployeePayload;
import com.hr.management.payload.ResponseBody;
import com.hr.management.repository.EmployeeRepository;
import com.hr.management.util.Constants;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public ResponseEntity<Object> createEmployee(EmployeePayload employeePayload) {

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<EmployeePayload>> violations = validator.validate(employeePayload);

		if (!violations.isEmpty()) {
			List<String> errorList = violations.stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.toList());
			return ResponseEntity.badRequest().body(new ResponseBody("HM-005", errorList.toString()));
		}
		if (employeeRepository.isEmailAlreadyExists(employeePayload.getEmail())) {
			return ResponseEntity.badRequest().body(new ResponseBody("HM- 006", MessageFormat
					.format(Constants.E_ALREADY_EXIST, Constants.EMAIL.toLowerCase(), employeePayload.getEmail())));
		}

		if (employeeRepository.isContactNumberAlreadyExists(employeePayload.getContactNumber())) {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM- 007", MessageFormat.format(Constants.E_ALREADY_EXIST,
							Constants.CONTACT_NUMBER.toLowerCase(), employeePayload.getContactNumber())));
		}

		Employee employee = new Employee();
		employee.setId(Constants.EMPL_PREFIX.concat(UUID.randomUUID().toString()));
		employee.setFirstName(employeePayload.getFirstName());
		employee.setLastName(employeePayload.getLastName());
		employee.setGender(employeePayload.getGender());
		employee.setEmail(employeePayload.getEmail());
		employee.setContactNumber(employeePayload.getContactNumber());
		employee.setAddress(employeePayload.getAddress());
		employee.setDesignation(employeePayload.getDesignation());
		employee.setDateOfJoining(employeePayload.getDateOfJoining());
		employee.setHighestQualification(employeePayload.getHighestQualification());
		employee.setPreviousEmployer(employeePayload.getPreviousEmployer());
		employee.setTotalExperience(employeePayload.getTotalExperience());
		employee.setCreatedAt(LocalDateTime.now());

		return ResponseEntity.ok(employeeRepository.save(employee));
	}

	@SuppressWarnings("unused")
	@Transactional
	public ResponseEntity<Object> updateEmployee(String id, EmployeePayload employeePayload) {
		Employee employeeId = employeeRepository.getById(id);
		if (employeeId != null) {
			employeeId.setFirstName(employeePayload.getFirstName());
			employeeId.setLastName(employeePayload.getLastName());
			employeeId.setGender(employeePayload.getGender());
			employeeId.setEmail(employeePayload.getEmail());
			employeeId.setContactNumber(employeePayload.getContactNumber());
			employeeId.setAddress(employeePayload.getAddress());
			employeeId.setDesignation(employeePayload.getDesignation());
			employeeId.setDateOfJoining(employeePayload.getDateOfJoining());
			employeeId.setHighestQualification(employeePayload.getHighestQualification());
			employeeId.setPreviousEmployer(employeePayload.getPreviousEmployer());
			employeeId.setTotalExperience(employeePayload.getTotalExperience());
			employeeId.setUpdatedAt(LocalDateTime.now());

			return ResponseEntity.ok(employeeRepository.save(employeeId));
		} else {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM- 003", MessageFormat.format("Admin not found.", id)));

		}
	}

	public ResponseEntity<List<Employee>> getEmployees() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(employeeRepository.getEmployees());
	}

	public ResponseEntity<Object> getEmployeeById(String id) {
		if (!id.startsWith(Constants.EMPL_PREFIX)) {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM-001", "Invalid ID format. ID should have the correct prefix."));
		}
		Employee employee = employeeRepository.getById(id);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM- 002", MessageFormat.format("Employee with id ''{0}'' not found.", id)));
		}
	}

	@Transactional
	public ResponseEntity<Object> deleteEmployee(String id) {
		Employee employee = employeeRepository.getById(id);
		if (employee != null) {
			employee.setIsDeleted(1);
			employeeRepository.save(employee);
			return ResponseEntity.ok("Employee with ID: " + id + " deleted successfully");
		} else {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM- 004", MessageFormat.format("Employee with id ''{0}'' not found.", id)));
		}

	}

	public ResponseEntity<Object> generateSalarySlipPdf(String employeeId) throws MalformedURLException, IOException {
		Employee employee = employeeRepository.getById(employeeId);

		if (employee == null) {
			return ResponseEntity.badRequest().body(new ResponseBody("HM-008", "Employee not found."));
		}

		double basicSalary = 5000; // Replace this with actual basic salary retrieval logic
		double deductions = 1000; // Replace this with actual deduction retrieval logic
		double grossSalary = calculateGrossSalary(basicSalary);
		double netSalary = calculateNetSalary(grossSalary, deductions);

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream("/Users/cipher/Documents/SalarySlip/salary_slip.pdf"));
			document.open();

			// Add content to the PDF
			/*
			 * document.add(new Paragraph("Employee Name: " + employee.getFirstName() + " "
			 * + employee.getLastName())); document.add(new Paragraph("Employee ID: " +
			 * employee.getId())); document.add(new Paragraph("Basic Salary: " +
			 * basicSalary)); document.add(new Paragraph("Gross Salary: " + grossSalary));
			 * document.add(new Paragraph("Deductions: " + deductions)); document.add(new
			 * Paragraph("Net Salary: " + netSalary));
			 */
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);

			// Add content to the table
			table.addCell("Employee Name");
			table.addCell("Employee ID");
			table.addCell("Salary Details");

			table.addCell(employee.getFirstName() + " " + employee.getLastName());
			table.addCell(employee.getId());

			// Add salary details in a nested table
			PdfPTable salaryTable = new PdfPTable(2);
			salaryTable.addCell("Basic Salary");
			salaryTable.addCell(String.valueOf(basicSalary));

			salaryTable.addCell("Gross Salary");
			salaryTable.addCell(String.valueOf(grossSalary));

			salaryTable.addCell("Deductions");
			salaryTable.addCell(String.valueOf(deductions));

			salaryTable.addCell("Net Salary");
			salaryTable.addCell(String.valueOf(netSalary));

			PdfPCell nestedCell = new PdfPCell(salaryTable);
			nestedCell.setColspan(3);
			table.addCell(nestedCell);

			document.add(table);

			// Add space after the table
			document.add(new Paragraph("\n")); // Empty paragraph for spacing

			// Add company information at the bottom left side
			Paragraph companyInfo = new Paragraph("For Percept Infosystem Consultants,\n" + "Pooja Bhosle \n"
					+ "HR Manager\n" + "Authorized Signatory\n" + "Human Resources");
			companyInfo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
			document.add(companyInfo);

			// Add signature
			Image signature = Image.getInstance("/Users/cipher/Documents/Signature.jpeg");
			signature.scaleToFit(100, 50); // Adjust the size of the signature as needed
			document.add(signature);
			document.close();
		} catch (DocumentException | FileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseBody("HM-009", "Error generating PDF."));
		}

		return ResponseEntity.ok().build(); // Successful response without any content
	}

	private double calculateGrossSalary(double basicSalary) {
		// Calculation logic for gross salary (e.g., including bonuses, allowances)
		return basicSalary + 1000; // Example: Adding a fixed bonus amount
	}

	private double calculateNetSalary(double grossSalary, double deductions) {
		// Calculation logic for net salary (e.g., accounting for taxes, insurance)
		return grossSalary - deductions;
	}

	public ResponseEntity<Object> generateReferenceLetter(String employeeId) throws MalformedURLException, IOException {
		// Fetch employee details from the repository using the employeeId
		Employee employee = employeeRepository.getById(employeeId);

		if (employee == null) {
			return ResponseEntity.badRequest().body(new ResponseBody("HM-010", "Employee not found."));
		}

		Document document = new Document();

		try {
			PdfWriter.getInstance(document,
					new FileOutputStream("/Users/cipher/Documents/Empl-ReferenceLetter/employee_reference_letter.pdf"));
			document.open();

			// Add content to the PDF
			Paragraph date = new Paragraph(
					"Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			document.add(date);

			Paragraph content = new Paragraph(employee.getFirstName() + " " + employee.getLastName()
					+ " holds the Business Title of " + employee.getDesignation()
					+ " and is a Regular employee of Percept Infosystem Consultants with " + "Employee ID" + " "
					+ employee.getId() + " from " + employee.getDateOfJoining() + ".");
			// Add space after the table
			document.add(new Paragraph("\n")); // Empty paragraph for spacing
			document.add(new Paragraph("\n"));

			document.add(content);

			// Add empty paragraph after joining date
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			// Add company information at the bottom left side
			Paragraph companyInfo = new Paragraph("For Percept Infosystem Consultants,\n" + "Pooja Bhosle\n"
					+ "HR Manager\n" + "Authorized Signatory\n" + "Human Resources");
			companyInfo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
			document.add(companyInfo);

			// Add signature
			Image signature = Image.getInstance("/Users/cipher/Documents/Signature.jpeg");
			signature.scaleToFit(100, 50); // Adjust the size of the signature as needed
			document.add(signature);

			document.close();
		} catch (DocumentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseBody("HM-011", "Error generating Reference letter."));
		}

		// Return the generated PDF response or file path as needed
		return ResponseEntity.ok(document);
	}

}
