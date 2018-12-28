package com.att.api.nobf.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Customer {
	
	private String username;
	private String registeredBusinessName;
	private String otherBusinessName;
	private String businessType;
	private String taxPayerIdOrEINNumber;
	
	private Contact primaryContact;
	private Address billingAddress;
	private Address shippingAddress;
	private Address installAddress;
}
