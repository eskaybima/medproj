package com.medicine.telemedicine.payload;
import javax.validation.constraints.*;
import java.util.Set;
public class SignUpRequestHosp {
	 @NotBlank
	    @Size(min = 4, max = 100)
	    private String name;


	    @NotBlank
	    @Size(max = 100)
	    @Email
	    private String email;
	    
	    @NotBlank
	    @Size(max = 80)
	    private String username;

	 
		@NotBlank
	    @Size(min = 6, max = 20)
	    private String password;
	   
	    
	    @NotBlank
	    @Size(max = 15)
	    private String phonenumber;
	    
	    
	    @NotBlank
	    @Size(max = 20)
		private String regNumber;
	    
	  

		@NotBlank
	    @Size(max = 80)
	    private String contactPerson;
	    
	    @NotBlank
	    @Size(max = 100)
	    private String services;
	    
	    @NotBlank
	    @Size(max = 80)
	    private String bank;
	    
	    @NotBlank
	    @Size(max = 20)
	    private String bankcode;
	    private String active;
	   private String usertype;


	

		public void setRole(Set<String> role) {
			this.role = role;
		}

		public String getBankcode() {
			return bankcode;
		}

		public void setBankcode(String bankcode) {
			this.bankcode = bankcode;
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

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public String getPhonenumber() {
	        return phonenumber;
	    }

	    public void setPhonenumber(String phonenumber) {
	        this.phonenumber = phonenumber;
	    }
	    
	    private String address ;
	    public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		  public String getRegNumber() {
				return regNumber;
			}

			public void setRegNumber(String regNumber) {
				this.regNumber = regNumber;
			}

		public String getContactPerson() {
			return contactPerson;
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
      
		   public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}
        
			 private Set<String> role;

			    public Set<String> getRole() {
					return role;
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

}
