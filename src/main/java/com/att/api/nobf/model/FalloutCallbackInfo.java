package com.att.api.nobf.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "falloutCallbackInfo")
public class FalloutCallbackInfo extends Auditable {

	@Id
	private String id;

	private String firstName;

	private String lastName;
	
	private String phoneNumber;
	
	private String altPhoneNumber;
	
	private String email;
	
	private String companyName;
	
	private String businessPhoneNumber;
	
	private Address businessAddress;
	
	private String suiteNumber;
	
	private Date  dateToCall ;
}