package com.att.api.nobf.security.model;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.att.api.nobf.utility.EmailConstants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecurityProduct {
	
	@NotNull
	private SecurityProductType securityProductType;
	@NotNull
	private ProductConfigurations productConfigurations; 
}
