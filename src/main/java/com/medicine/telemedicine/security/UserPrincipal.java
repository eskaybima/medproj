package com.medicine.telemedicine.security;
import com.medicine.telemedicine.model.Hospital;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Long id;
	    

		private String name;
	    private String username;
	    
	    @JsonIgnore
	    private String email;
	    @JsonIgnore
	    private String password;
	    private String address;
		private String regNumber;
		private String phonenumber;
		private String contactPerson;
		private String services;
		private String bank;
		private String bankcode ;
	

		private Collection<? extends GrantedAuthority> authorities;
		
	    public UserPrincipal(Long id, String name, String username, String email, String password, String address, String regNumber,String phonenumber, String contactPerson, String services, String bank ,String bankcode, Collection<? extends GrantedAuthority> authorities) {
	        this.id = id;
	        this.name = name;
	        this.username = username;
	        this.email = email;
	        this.password = password;
	        this.address = address;
	        this.regNumber = regNumber ;
	        this.phonenumber = phonenumber ;
	        this.contactPerson = contactPerson ;
	        this.services = services;
	        this.bank = bank;
	        this.bankcode = bankcode ;
	        this.authorities = authorities;
	    }

	    public String getRegNumber() {
			return regNumber;
		}

		public void setRegNumber(String regNumber) {
			this.regNumber = regNumber;
		}

		public static UserPrincipal create(Hospital hosp) {
	        List<GrantedAuthority> authorities = hosp.getRoles().stream().map(role ->
	                new SimpleGrantedAuthority(role.getName().name())
	        ).collect(Collectors.toList());

	        return new UserPrincipal(
	        		hosp.getId(),
	        		hosp.getName(),
	        		hosp.getUsername(),
	        		hosp.getEmail(),
	        		hosp.getPassword(),
	        		hosp.getAddress(),
	        		hosp.getRegNumber(),
	        		hosp.getPhonenumber(),
	        		hosp.getContactPerson(),
	        		hosp.getServices(),
	        		hosp.getBank(),
	        		hosp.getBankcode(),
	                authorities
	        );
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

	    public String getEmail() {
	        return email;
	    }

	    @Override
	    public String getUsername() {
	        return username;
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }
	    
	    public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
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
	/**
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

		public String getUser_type() {
			return user_type;
		}

		public void setUser_type(String user_type) {
			this.user_type = user_type;
		}

		public String getUsertype() {
			return usertype;
		}

		public void setUsertype(String usertype) {
			this.usertype = usertype;
		}
 **/

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return authorities;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        UserPrincipal that = (UserPrincipal) o;
	        return Objects.equals(id, that.id);
	    }

	    @Override
	    public int hashCode() {

	        return Objects.hash(id);
	    }

}
