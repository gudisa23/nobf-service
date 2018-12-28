package com.att.api.nobf.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductConfigurations {
	
	private String numberOfUsers;
	private int quantity;
	private String utmEnabled;
	private String installationMethod;
	private String deviceName;
	private int consultingHours;
	private String serviceType;

}
