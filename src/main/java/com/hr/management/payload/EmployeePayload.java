package com.hr.management.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmployeePayload {

	@NotEmpty(message = "First name must not be empty")
	@Size(max = 255)
	@Pattern(regexp = "^(?!\\d*$).+", message = "First name must not contain digits")
	private String firstName;
	
	@NotEmpty(message = "Last name must not be empty")
    @Size(max = 255)
	@Pattern(regexp = "^(?!\\d*$).+", message = "Last name must not contain digits")
	private String lastName;
	
	@NotEmpty(message = "Gender must not be empty")
	private String gender;
	
	@NotEmpty(message = "Email must not be empty")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotEmpty(message = "Contact Number must not be empty")
	@Pattern(regexp = "\\d+", message = "Contact number should contain only digits")
	private String contactNumber;
	
	@NotEmpty(message = "Address must not be empty")
	private String address;
	
	@NotEmpty(message = "Designation must not be empty")
	private String designation;
	
	@NotEmpty(message = "Date Of Joining must not be empty")
	private String dateOfJoining;
	
	@NotEmpty(message = "Highest Qualification must not be empty")
	private String highestQualification;
	
	@NotEmpty(message = "Previous Employer must not be empty")
	private String previousEmployer;
	
	@NotEmpty(message = "Total Experience must not be empty")
	private String totalExperience;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public String getPreviousEmployer() {
		return previousEmployer;
	}
	public void setPreviousEmployer(String previousEmployer) {
		this.previousEmployer = previousEmployer;
	}
	public String getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	
}
