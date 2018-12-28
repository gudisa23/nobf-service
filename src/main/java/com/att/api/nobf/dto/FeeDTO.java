package com.att.api.nobf.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.att.api.nobf.model.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class FeeDTO {
	
	@NotNull
	private String name;
	
	private String description;
	
	@Positive
	private float feeAmount;
	
	private CurrencyType currencyType;
	
}
