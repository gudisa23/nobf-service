package com.att.api.nobf.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.att.api.nobf.model.OrderType;
import com.att.api.nobf.model.ScheduleInstallation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	  private String id;
	  
	  private OrderType orderType;
	  
	  @NotNull
	  private List<ServiceDTO> services;
	  
	  @NotNull
	  private CustomerDTO customer;
	  
	  private int agreementDurationInMonths;
	  
	  @NotNull
	  private ScheduleInstallation scheduleInstallation;
}

