package com.att.api.nobf.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor
public class Fee {
	
	@NonNull private String name;
	
	private String description;
	
	private float feeAmount;
	
	private CurrencyType currencyType;
	
}
