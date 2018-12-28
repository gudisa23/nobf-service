package com.att.api.nobf.commercial;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "quotedMonthlyCharges", "quotedTodaysCharges" })
public class CartDetails {

	@JsonProperty("quotedMonthlyCharges")
	private Double quotedMonthlyCharges;
	@JsonProperty("quotedTodaysCharges")
	private Double quotedTodaysCharges;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("quotedMonthlyCharges")
	public Double getQuotedMonthlyCharges() {
		return quotedMonthlyCharges;
	}

	@JsonProperty("quotedMonthlyCharges")
	public void setQuotedMonthlyCharges(Double quotedMonthlyCharges) {
		this.quotedMonthlyCharges = quotedMonthlyCharges;
	}

	@JsonProperty("quotedTodaysCharges")
	public Double getQuotedTodaysCharges() {
		return quotedTodaysCharges;
	}

	@JsonProperty("quotedTodaysCharges")
	public void setQuotedTodaysCharges(Double quotedTodaysCharges) {
		this.quotedTodaysCharges = quotedTodaysCharges;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}