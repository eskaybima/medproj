package com.medicine.telemedicine.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.medicine.telemedicine.model.audit.DateAudit;


@Entity
@Table(name = "user_login")

public class Login extends DateAudit {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;
    
    @NotBlank
	@Size(max = 100)
	private String User_type;
    
    
	public Login() {

    }
	
	public Login(String email,String password,String User_type ) {
		this.email = email;
		this.password = password ;
		this.User_type = User_type ;

    }
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return User_type;
	}

	public void setUser_type(String user_type) {
		User_type = user_type;
	}

	

}
