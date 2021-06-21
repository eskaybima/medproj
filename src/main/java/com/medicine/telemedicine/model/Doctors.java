package com.medicine.telemedicine.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.NaturalId;
import com.medicine.telemedicine.model.audit.DateAudit;

@Entity
@Table(name = "hospitals", uniqueConstraints = {

		@UniqueConstraint(columnNames = { "email" }), @UniqueConstraint(columnNames = { "phonenumber" }) })


public class Doctors extends DateAudit{
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@Column (columnDefinition = "varchar default 'N'")
	private String active;
	
	private String usertype ;
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
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	
	public Doctors() {

    }
	
	
	public Doctors(String first_name,String last_name,String middle_name,String dob,String phonenumber,String email,String address,String gender,String username,String password,String usertype,String med_school,String year_graduation,String practice_no,String affiliate_hospital,String image,String specialty,String current_address) {
		this.first_name =  first_name ;
		this.last_name   =  last_name; 
		this.middle_name =  middle_name;
		this.dob  = dob;
		this.phonenumber  = phonenumber;
		this.email =  email;
		this.address = address;
		this.gender = gender;
	    this.username = username;
		this.password  = password;
		this.usertype = usertype;
		this.med_school = med_school;
		this.year_graduation = year_graduation;
		this.practice_no = practice_no;
		this.affiliate_hospital = affiliate_hospital;
		this.image  = image;
		this.specialty = specialty;
		this.current_address = current_address;		 

    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	public String getUsertype() {
		return usertype;
	}


	public void setUsertype(String usertype) {
		this.usertype = usertype;
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


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

}
