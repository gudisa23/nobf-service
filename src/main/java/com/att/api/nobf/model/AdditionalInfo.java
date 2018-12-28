package com.att.api.nobf.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "additionalInfo")
public class AdditionalInfo extends Auditable {

	@Id
	private String id;

	private String companyName;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private String state;
}