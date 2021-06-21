package com.medicine.telemedicine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
	
	    private String object;
	    private String field;
	    private Object rejectedValue;
	    private String message;

	    ApiValidationError(String object, String message) {
	        this.object = object;
	        this.message = message;
	    }
	
	    ApiValidationError(String object, String field, Object rejectedValue, String message) {
	        this.object = object;
	        this.message = message;
	        this.field = field;
	        this.rejectedValue = rejectedValue ;
	    }

}
