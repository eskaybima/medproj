package com.medicine.telemedicine.payload;
import java.util.Set;

import javax.validation.constraints.*;
import org.hibernate.annotations.NaturalId;



public class SignUpRequestPatient {
	

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
	
	@NotBlank
	@Size(max = 50)
	private String gov_id;
	
	@NotBlank
	@Size(max = 100)
	private String allergies;
	
	@NotBlank
	@Size(max = 10)
	private String weight;
	
	@NotBlank
	@Size(max = 10)
	private String height;
	
	@NotBlank
	@Size(max = 10)
	private String blood_group;
	
	@NotBlank
	@Size(max = 10)
	private String genotype;
	
	private Set<String> role;
	 @NotBlank
		@Size(max = 20)
	 private String usertype;
	 
	 
	 private String other_no ;

	public String getOther_no() {
		return other_no;
	}

	public void setOther_no(String other_no) {
		this.other_no = other_no;
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

	public String getGov_id() {
		return gov_id;
	}

	public void setGov_id(String gov_id) {
		this.gov_id = gov_id;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getGenotype() {
		return genotype;
	}

	public void setGenotype(String genotype) {
		this.genotype = genotype;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	 
	
}
