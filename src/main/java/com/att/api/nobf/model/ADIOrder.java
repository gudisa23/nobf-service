package com.att.api.nobf.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
@Document(collection = "orders")
public class ADIOrder extends Auditable {
	
	  @Id private String id;
	  
	  private OrderType orderType;
	  
	  private List<Service> services;
	  
	  private Customer customer;
	  
	  private int agreementDurationInMonths;
	  
	  private ScheduleInstallation scheduleInstallation;
}