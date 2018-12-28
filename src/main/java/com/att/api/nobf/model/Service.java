package com.att.api.nobf.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Service {

	@NonNull
	private String name;

	private String description;

	private String speed;

	private String interfaceName;
	
	private String numberOfUsers;
	
	private InstallationType installationType;
}
