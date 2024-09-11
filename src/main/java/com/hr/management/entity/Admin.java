	package com.hr.management.entity;
	
	import java.time.LocalDateTime;
	
	import javax.persistence.Entity;
	import javax.persistence.Id;
	import javax.persistence.Table;
	
	@Entity
	@Table(name = "admin")
	public class Admin {
		
		@Id
		private String id;
		private String username;
		private String password;
		private String firstName;
		private String lastName;
		private String gender;
		private String email;
		private String contactNumber;
		private String address;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		private int isDeleted;
	
		/*
		 * public Admin(String string, String email2) { super(); }
		 */
	
		public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
		}
		
	
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
	
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
	
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
	
		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}
	
		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
	
		public int getIsDeleted() {
			return isDeleted;
		}
	
		public void setIsDeleted(int isDeleted) {
			this.isDeleted = isDeleted;
		}
	
	}