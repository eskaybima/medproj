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

public class Patient extends DateAudit {

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

	@NotBlank
	@Size(max = 40)
	private String gov_id;

	private String dob;

	@NotBlank
	@Size(max = 15)
	private String phonenumber;

	

	@NotBlank
	@Size(max = 15)
	private String other_no;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(max = 150)
	private String address;

	@NotBlank
	@Size(max = 20)
	private String gender;

	@NotBlank
	@Size(max = 40)
	private String allergies;

	@NotBlank
	@Size(max = 10)
	private String weight;

	@NotBlank
	@Size(max = 10)
	private String height;

	@NotBlank
	@Size(max = 20)
	private String blood_group;

	@NotBlank
	@Size(max = 20)
	private String genotype;

	@NotBlank
	@Size(max = 100)
	private String username;

	

	@NotBlank
	@Size(max = 100)
	private String password;
	
	
	@Column (columnDefinition = "varchar default 'N'")
	private String active;
	
	private String user_type ;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


	




	

	public Patient() {

    }
	
	public Patient(String first_name,String last_name,String middle_name,String gov_id,String dob,String phonenumber,String other_no,String email,String address,String gender,String allergies,String weight,String height,String blood_group,String genotype,String username,String password,String user_type) {
		this.first_name =  first_name ;
		this.last_name   =  last_name; 
		this.middle_name =  middle_name;
		this.gov_id = gov_id;
		this.dob  = dob;
		this.phonenumber  = phonenumber;
		this.other_no  = other_no;
		this.email =  email;
		this.address = address;
		this.gender = gender;
		this.allergies  = allergies;
		this.weight  = weight;
		this.height = height;
		this.blood_group = blood_group;
		this.genotype = genotype; 
		this.username = username;
		this.password  = password;
		this.user_type = user_type;

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

	public String getGov_id() {
		return gov_id;
	}

	public void setGov_id(String gov_id) {
		this.gov_id = gov_id;
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

	public String getOther_no() {
		return other_no;
	}

	public void setOther_no(String other_no) {
		this.other_no = other_no;
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
	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
