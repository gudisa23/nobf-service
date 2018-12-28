package com.att.api.nobf.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Contact {
	
	private String firstName;
	
	private String lastName;
	
	private String primaryNumber;
	
	private String secondaryNumber;
	
	private String emailAddress;
	
	// TBD: remove this, may not be required, check references before removing
	private boolean isPrimary;
	
	private String secondaryContactName;
	
	private String secondaryEmailAddress;
}
