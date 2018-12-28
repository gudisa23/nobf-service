package com.att.api.nobf.security.model;

import com.att.api.nobf.model.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecurityContact {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private Address address;
}
