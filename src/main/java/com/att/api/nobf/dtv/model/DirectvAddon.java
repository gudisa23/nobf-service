package com.att.api.nobf.dtv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectvAddon extends DirectvProduct {

	@JsonProperty("NA")
	Double monthlyPrice;
	@JsonProperty("limitedPaymentTerms")
	Integer totalMonthlyPayments;
	
	@JsonProperty("limitedPaymentTerms")
	public Integer getTotalMonthlyPayments() {
		return totalMonthlyPayments;
	}

	@JsonProperty("limitedPaymentTerms")
	public void setTotalMonthlyPayments(Integer totalMonthlyPayments) {
		this.totalMonthlyPayments = totalMonthlyPayments;
	}

	@JsonProperty("NA")
	public Double getMonthlyPrice() {
		return monthlyPrice;
	}

	@JsonProperty("NA")
	public void setMonthlyPrice(Double monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}
	
	
}
