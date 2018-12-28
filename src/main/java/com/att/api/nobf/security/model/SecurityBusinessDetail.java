package com.att.api.nobf.security.model;

import com.att.api.nobf.model.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecurityBusinessDetail {

	private String businessName;
	private String doingBusinessAsName;
	private Address address;
}
