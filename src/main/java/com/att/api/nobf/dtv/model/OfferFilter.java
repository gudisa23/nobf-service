package com.att.api.nobf.dtv.model;

import javax.validation.constraints.Pattern;

import com.att.api.nobf.utility.ServiceConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferFilter {
	@JsonProperty("filterID")
	Integer filterID;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("accountType")
	String AccountType;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("BusinessSegment")
	String BusinessSegment;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("EvoRange")
	String EvoRange;
	@JsonProperty("FCORange")
	Integer FCORange;
	@JsonProperty("spanish")
	Boolean spanish;
	
	@JsonProperty("accountType")
	public String getAccountType() {
		return AccountType;
	}
	@JsonProperty("filterID")
	public Integer getFilterID() {
		return filterID;
	}
	@JsonProperty("filterID")
	public void setFilterID(Integer filterID) {
		this.filterID = filterID;
	}
	@JsonProperty("accountType")
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	@JsonProperty("BusinessSegment")
	public String getBusinessSegment() {
		return BusinessSegment;
	}
	@JsonProperty("BusinessSegment")
	public void setBusinessSegment(String businessSegment) {
		BusinessSegment = businessSegment;
	}
	@JsonProperty("FCORange")
	public Integer getFCORange() {
		return FCORange;
	}
	@JsonProperty("FCORange")
	public void setFCORange(Integer fCORange) {
		FCORange = fCORange;
	}
	@JsonProperty("spanish")
	public Boolean getSpanish() {
		return spanish;
	}
	@JsonProperty("spanish")
	public void setSpanish(Boolean spanish) {
		this.spanish = spanish;
	}
	@JsonProperty("EvoRange")
	public String getEvoRange() {
		return EvoRange;
	}
	@JsonProperty("EvoRange")
	public void setEvoRange(String evoRange) {
		EvoRange = evoRange;
	}
	
	
	
}
