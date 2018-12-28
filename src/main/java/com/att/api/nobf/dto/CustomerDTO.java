package com.att.api.nobf.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	private String username;
	private String registeredBusinessName;
	private String otherBusinessName;
	private String businessType;
	private String taxPayerIdOrEINNumber;
	
	private ContactDTO primaryContact;
	private AddressDTO billingAddress;
	private AddressDTO shippingAddress;
	private AddressDTO installAddress;
}
