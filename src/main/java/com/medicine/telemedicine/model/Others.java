package com.medicine.telemedicine.model;

import java.io.Serializable;

public abstract class Others implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String first_name;
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	private String last_name;

}
