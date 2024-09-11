package com.hr.management.service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import com.hr.management.entity.Admin;
import com.hr.management.payload.AdminPayload;
import com.hr.management.payload.ResponseBody;
import com.hr.management.repository.AdminRepository;
import com.hr.management.util.Constants;
import java.util.ArrayList;

@Service
@Validated
public class AdminService {

	@Autowired
	AdminRepository adminRepository;

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	public List<String> extractErrors(BindingResult bindingResult) {
		List<String> errorList = new ArrayList<>();

		for (FieldError e : bindingResult.getFieldErrors()) {
			errorList.add(e.getDefaultMessage());
		}

		return errorList;
	}

	@Transactional
	public ResponseEntity<Object> createAdmin(AdminPayload adminPayload) {

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<AdminPayload>> violations = validator.validate(adminPayload);

		if (!violations.isEmpty()) {
			List<String> errorList = violations.stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.toList());
			return ResponseEntity.badRequest().body(new ResponseBody("HM-005", errorList.toString()));
		}

		if (adminRepository.isEmailExists(adminPayload.getEmail())) {
			return ResponseEntity.badRequest().body(new ResponseBody("HM- 001", MessageFormat
					.format(Constants.A_ALREADY_EXIST, Constants.EMAIL.toLowerCase(), adminPayload.getEmail())));
		}

		if (adminRepository.isContactNumberExists(adminPayload.getContactNumber())) {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM- 002", MessageFormat.format(Constants.A_ALREADY_EXIST,
							Constants.CONTACT_NUMBER.toLowerCase(), adminPayload.getContactNumber())));
		}

		Admin admin = new Admin();
		admin.setId(Constants.ADMIN_PREFIX.concat(UUID.randomUUID().toString()));
		admin.setUsername(adminPayload.getUsername());
		admin.setPassword(adminPayload.getPassword());
		admin.setFirstName(adminPayload.getFirstName());
		admin.setLastName(adminPayload.getLastName());
		admin.setEmail(adminPayload.getEmail());
		admin.setGender(adminPayload.getGender());
		admin.setContactNumber(adminPayload.getContactNumber());
		admin.setAddress(adminPayload.getAddress());
		admin.setCreatedAt(LocalDateTime.now());

		adminRepository.save(admin);
	    return ResponseEntity.status(HttpStatus.CREATED).body("Admin created successfully");
	}

	@Transactional
	public ResponseEntity<Object> updateAdmin(String id, AdminPayload adminPayload) {
		Admin adminId = adminRepository.getById(id);

		if (adminId != null) {
			adminId.setFirstName(adminPayload.getFirstName());
			adminId.setLastName(adminPayload.getLastName());
			adminId.setGender(adminPayload.getGender());
			adminId.setAddress(adminPayload.getAddress());
			adminId.setUpdatedAt(LocalDateTime.now());
			adminId.setEmail(adminPayload.getEmail());
			adminId.setContactNumber(adminPayload.getContactNumber());

			adminRepository.save(adminId);

			return ResponseEntity.ok("Admin record updated successfully");

		} else {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM- 004", MessageFormat.format("Admin not found.", id)));
		}
	}

	public ResponseEntity<List<Admin>> getAdmins() {
		return ResponseEntity.ok(adminRepository.getAdmins());
	}

	public ResponseEntity<Object> getAdminById(String id) {

		if (!id.startsWith(Constants.ADMIN_PREFIX)) {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM-006", "Invalid ID format. ID should have the correct prefix."));
		}
		Admin admin = adminRepository.getById(id);
		if (admin != null) {
			return ResponseEntity.ok(admin);
		} else {
			return ResponseEntity.badRequest()
					.body(new ResponseBody("HM- 002", MessageFormat.format("Admin with id ''{0}'' not found.", id)));
		}
	}

	/*
	 * @Transactional public ResponseEntity<Object> deleteAdmin(String id) { Admin
	 * admin = adminRepository.getById(id); if (Objects.nonNull(admin)) {
	 * admin.setIsDeleted(1); adminRepository.save(admin); return
	 * ResponseEntity.ok("Admin with ID: " + id + " deleted successfully"); } else {
	 * return ResponseEntity.badRequest() .body(new ResponseBody("HM- 003",
	 * MessageFormat.format("Admin with id ''{0}'' not found.", id))); } }
	 * 
	 * 
	 * public ResponseEntity<Object> adminLogin(String username, String password) {
	 * Admin admin = adminRepository.findByUsername(username); if (admin != null &&
	 * passwordMatches(admin.getPassword(), password)) { // Return a token or manage
	 * the session upon successful login return
	 * ResponseEntity.ok("Admin Login Successful"); } // Handle incorrect password
	 * or username here without specific details return
	 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); }
	 * public String encodePassword(String password) { return
	 * passwordEncoder.encode(password); }
	 * 
	 * private boolean passwordMatches(String hashedPassword, String rawPassword) {
	 * return passwordEncoder.matches(rawPassword, hashedPassword); }
	 */
	 
}
