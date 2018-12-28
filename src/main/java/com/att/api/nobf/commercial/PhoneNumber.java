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
@JsonPropertyOrder({ "phoneAreaCode", "phoneNumber" })
public class PhoneNumber {

	@JsonProperty("phoneAreaCode")
	private Integer phoneAreaCode;
	@JsonProperty("phoneNumber")
	private Integer phoneNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("phoneAreaCode")
	public Integer getPhoneAreaCode() {
		return phoneAreaCode;
	}

	@JsonProperty("phoneAreaCode")
	public void setPhoneAreaCode(Integer phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}

	@JsonProperty("phoneNumber")
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
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
