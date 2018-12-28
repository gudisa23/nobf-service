package com.att.api.nobf.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.att.api.nobf.model.Address;
import com.att.api.nobf.model.OrderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class FalloutCallbackInfoDTO {

	@Id
	private String id;

	private String firstName;

	private String lastName;
	
	private String phoneNumber;
	
	private String altPhoneNumber;
	
	private String email;
	
	private String companyName;
	
	private String businessPhoneNumber;
	
	private AddressDTO businessAddress;
	
	private String suiteNumber;
	
	private Date  dateToCall ;

}

