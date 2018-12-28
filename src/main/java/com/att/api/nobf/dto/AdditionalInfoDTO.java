package com.att.api.nobf.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.att.api.nobf.model.OrderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class AdditionalInfoDTO {

	private String id = "";
	
	private String companyName;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private String state;

}

