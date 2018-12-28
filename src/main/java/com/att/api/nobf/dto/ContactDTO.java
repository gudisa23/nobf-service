package com.att.api.nobf.dto;

import lombok.Data;

@Data
public class ContactDTO {
	
	private String firstName;
	
	private String lastName;
	
	private String primaryNumber;
	
	private String secondaryNumber;
	
	private String emailAddress;
	
	private boolean isPrimary;
	
	private String secondaryContactName;
	
	private String secondaryEmailAddress;
}
