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
@JsonPropertyOrder({ "address2", "city", "secondaryUnitDesignator", "secondaryUnitNumber", "state", "streetName",
		"streetNumber", "zipCode" })
public class ServiceAddress {

	@JsonProperty("address2")
	private String address2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("secondaryUnitDesignator")
	private String secondaryUnitDesignator;
	@JsonProperty("secondaryUnitNumber")
	private String secondaryUnitNumber;
	@JsonProperty("state")
	private String state;
	@JsonProperty("streetName")
	private String streetName;
	@JsonProperty("streetNumber")
	private Integer streetNumber;
	@JsonProperty("zipCode")
	private String zipCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("address2")
	public String getAddress2() {
		return address2;
	}

	@JsonProperty("address2")
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("secondaryUnitDesignator")
	public String getSecondaryUnitDesignator() {
		return secondaryUnitDesignator;
	}

	@JsonProperty("secondaryUnitDesignator")
	public void setSecondaryUnitDesignator(String secondaryUnitDesignator) {
		this.secondaryUnitDesignator = secondaryUnitDesignator;
	}

	@JsonProperty("secondaryUnitNumber")
	public String getSecondaryUnitNumber() {
		return secondaryUnitNumber;
	}

	@JsonProperty("secondaryUnitNumber")
	public void setSecondaryUnitNumber(String secondaryUnitNumber) {
		this.secondaryUnitNumber = secondaryUnitNumber;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("streetName")
	public String getStreetName() {
		return streetName;
	}

	@JsonProperty("streetName")
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@JsonProperty("streetNumber")
	public Integer getStreetNumber() {
		return streetNumber;
	}

	@JsonProperty("streetNumber")
	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	@JsonProperty("zipCode")
	public String getZipCode() {
		return zipCode;
	}

	@JsonProperty("zipCode")
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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