package com.medicine.telemedicine.payload;
import javax.validation.constraints.*;

import org.hibernate.annotations.NaturalId;

import java.util.Set;

public class SignUpRequestDoc {

	@NotBlank
	@Size(max = 60)
	private String first_name;

	@NotBlank
	@Size(max = 60)
	private String last_name;

	@NotBlank
	@Size(max = 60)
	private String middle_name;


	private String dob;

	@NotBlank
	@Size(max = 15)
	private String phonenumber;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(max = 200)
	private String address;

	@NotBlank
	@Size(max = 20)
	private String gender;

	@NotBlank
	@Size(max = 20)
    private String username;
	

	@NotBlank
	@Size(max = 100)
	private String password;
	
	private String user_type ;
	@NotBlank
	@Size(max = 100)
	private String med_school;
	@NotBlank
	@Size(max = 20)
	private String year_graduation;
	@NotBlank
	@Size(max = 20)
	private String practice_no;
	@NotBlank
	@Size(max = 200)
	private String affiliate_hospital;
	private String image;
	@NotBlank
	@Size(max = 200)
	private String specialty;
	@NotBlank
	@Size(max = 200)
	private String current_address;
	
	 private Set<String> role;
	 @NotBlank
		@Size(max = 20)
	 private String usertype;
	 
	 
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getMed_school() {
		return med_school;
	}
	public void setMed_school(String med_school) {
		this.med_school = med_school;
	}
	public String getYear_graduation() {
		return year_graduation;
	}
	public void setYear_graduation(String year_graduation) {
		this.year_graduation = year_graduation;
	}
	public String getPractice_no() {
		return practice_no;
	}
	public void setPractice_no(String practice_no) {
		this.practice_no = practice_no;
	}
	public String getAffiliate_hospital() {
		return affiliate_hospital;
	}
	public void setAffiliate_hospital(String affiliate_hospital) {
		this.affiliate_hospital = affiliate_hospital;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getCurrent_address() {
		return current_address;
	}
	public void setCurrent_address(String current_address) {
		this.current_address = current_address;
	}


}
