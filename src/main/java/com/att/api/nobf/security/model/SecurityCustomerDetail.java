package com.att.api.nobf.security.model;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecurityCustomerDetail {
	
	@NotNull
	private SecurityContact primaryContact;

	private SecurityContact secondaryContact;

	@NotNull
	private SecurityBusinessDetail businessDetail;
}
