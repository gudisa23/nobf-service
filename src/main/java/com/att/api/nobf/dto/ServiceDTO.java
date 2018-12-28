package com.att.api.nobf.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.att.api.nobf.model.InstallationType;
import com.att.api.nobf.model.ScheduleInstallation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
	
	@NotNull
	private String name;
	
	private String description;
	
	@NotNull
	private String speed;
	
	@NotNull
	private String interfaceName;
	
	@NotNull
	private String numberOfUsers;
	
	@NotNull
	private InstallationType installationType;
}

