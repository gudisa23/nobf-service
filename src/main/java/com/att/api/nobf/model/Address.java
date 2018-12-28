package com.att.api.nobf.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Address {

	private String addressLine1;
	
	private String addressLine2;
	
	private String city;

	private String state;
	
	private String zip;
	
}
