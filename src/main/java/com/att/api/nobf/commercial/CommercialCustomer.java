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
@JsonPropertyOrder({ "accountType", "serviceAddress", "billingAddress", "businessName", "businessSegment",
		"contactName", "emailAddress", "phoneNumber", "evo", "fco" })
public class CommercialCustomer {

	@JsonProperty("accountType")
	private String accountType;
	@JsonProperty("serviceAddress")
	private ServiceAddress serviceAddress;
	@JsonProperty("billingAddress")
	private BillingAddress billingAddress;
	@JsonProperty("businessName")
	private String businessName;
	@JsonProperty("businessSegment")
	private String businessSegment;
	@JsonProperty("contactName")
	private String contactName;
	@JsonProperty("emailAddress")
	private String emailAddress;
	@JsonProperty("phoneNumber")
	private PhoneNumber phoneNumber;
	@JsonProperty("evo")
	private String evo;
	@JsonProperty("fco")
	private Integer fco;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("accountType")
	public String getAccountType() {
		return accountType;
	}

	@JsonProperty("accountType")
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@JsonProperty("serviceAddress")
	public ServiceAddress getServiceAddress() {
		return serviceAddress;
	}

	@JsonProperty("serviceAddress")
	public void setServiceAddress(ServiceAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	@JsonProperty("billingAddress")
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	@JsonProperty("billingAddress")
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	@JsonProperty("businessName")
	public String getBusinessName() {
		return businessName;
	}

	@JsonProperty("businessName")
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@JsonProperty("businessSegment")
	public String getBusinessSegment() {
		return businessSegment;
	}

	@JsonProperty("businessSegment")
	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}

	@JsonProperty("contactName")
	public String getContactName() {
		return contactName;
	}

	@JsonProperty("contactName")
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	@JsonProperty("emailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@JsonProperty("phoneNumber")
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("evo")
	public String getEvo() {
		return evo;
	}

	@JsonProperty("evo")
	public void setEvo(String evo) {
		this.evo = evo;
	}

	@JsonProperty("fco")
	public Integer getFco() {
		return fco;
	}

	@JsonProperty("fco")
	public void setFco(Integer fco) {
		this.fco = fco;
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
