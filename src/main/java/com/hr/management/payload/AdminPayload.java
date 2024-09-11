package com.hr.management.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AdminPayload {

	@NotEmpty(message = "Username must not be empty")
	private String username;
	
	@NotEmpty(message = "Password must not be empty")
	private String password;
	
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
	
	@NotEmpty(message = "Contact number must not be empty")
	@Pattern(regexp = "\\d+", message = "Contact number should contain only digits")
	private String contactNumber;
	
	@NotEmpty(message = "Address must not be empty")
	private String address;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	
	
}
