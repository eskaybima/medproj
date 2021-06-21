package com.medicine.telemedicine.model;

import com.medicine.telemedicine.model.audit.DateAudit;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hospitals", uniqueConstraints = {

		 @UniqueConstraint(columnNames = {
		            "username"
		        }),
		        @UniqueConstraint(columnNames = {
		            "email"
		        }),
		        @UniqueConstraint(columnNames = {
		                "phonenumber"
		            })
		})

public class Hospital extends DateAudit {
	
	
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 

	@NotBlank
	    @Size(max = 100)
	private String name;
	  @NaturalId
	    @NotBlank
	    @Size(max = 100)
	    @Email 
	private String email;
	  @NotBlank
	    @Size(max = 100) 
	private String username;
	  @NotBlank
	    @Size(max = 100)
	private String password;
	  @NotBlank
	    @Size(max = 150)
	private String address;
	  @NotBlank
	    @Size(max = 20)
	

	@NotBlank
	    @Size(max = 40)
	private String phonenumber;
	  @NotBlank
	    @Size(max = 60)
	private String contactPerson;
	  @NotBlank
	    @Size(max = 200)
	private String services;
	  @NotBlank
	    @Size(max = 60)
	private String bank;
	  @NotBlank
	    @Size(max = 20)
	private String bankcode ;
	@Column (columnDefinition = "varchar default 'N'")
	private String active;
	private String usertype;
	
	
	 

	@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_roles",
	            joinColumns = @JoinColumn(name = "hospital_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<>();
	
	
	public Hospital() {

    }
	
	public Hospital(String name,String email,String username,String password,String address,String regNumber,String phonenumber,String contactPerson,String services,String bank,String bankcode,String usertype ) {
		this.name = name;
		this.email = email ;
		this.username = username;
		this.password = password;
		this.address = address;
		this.regNumber = regNumber;
		this.phonenumber = phonenumber;
		this.contactPerson = contactPerson ;
		this.services = services;
		this.bank = bank;
		this.bankcode = bankcode;
		this.usertype = usertype;
		

    }
	
	 public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String regNumber;
	  public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}


	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	
	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
 
	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}


	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getContactPerson() {
		return contactPerson;
	}
    	
	
	 public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }
	    public String getUsertype() {
			return usertype;
		}

		public void setUsertype(String usertype) {
			this.usertype = usertype;
		}
}
