package com.att.api.nobf.security.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.att.api.nobf.model.Auditable;
import com.att.api.nobf.security.constants.SecurityConstants;
import com.att.api.nobf.security.validation.SecurityInternetSpeedConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
@Document(collection ="securityorder")
public class SecurityOrder extends Auditable {
	
	  @Id 
	  private String id;
	  private String timeStamp;
	  
	  @NotNull
	  private SecurityCustomerDetail customerDetail;
	  
	  @NotNull
	  private int agreementDurationInMonths;
	  
	  @NotNull
	  @SecurityInternetSpeedConstraint
	  private String internetSpeed;
	  
	  @NotEmpty
	  private List<SecurityProduct> products;

}
