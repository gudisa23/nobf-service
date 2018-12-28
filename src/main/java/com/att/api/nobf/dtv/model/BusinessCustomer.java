package com.att.api.nobf.dtv.model;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.att.api.nobf.utility.ServiceConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({ "accountType", "serviceAddress", "billingAddress", "businessName", "businessSegment", "contactName", "emailAddress", "phoneNumber", "evo", "fco" })
@Document(collection ="dtvrfi")
public class BusinessCustomer {

	@Id
	private String id;
	private String timeStamp;
	
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("addressLine1")
	private String addressLine1;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("addressLine2")
	private String addressLine2;
	@JsonProperty("numberOfFloors")
	private Integer numberOfFloors;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("businessName")
	private String businessName;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("billingAddress")
	private String billingAddress;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("firstName")
	private String firstName;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("lastName")
	private String lastName;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("emailAddress")
	private String emailAddress;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("callBackPreference")
	private String callBackPreference;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("callBackTiming")
	private String callBackTiming;
	
	@JsonProperty("callBackNotes")
	private String callBackNotes;
	
	@JsonProperty("addressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}
	@JsonProperty("addressLine1")
	public void setAddressLine1(String address) {
		this.addressLine1 = address;
	}
	@JsonProperty("addressLine2")
	public void setAddressLine2(String address) {
		this.addressLine2 = address;
	}
	@JsonProperty("addressLine2")
	public String getAddressLine2() {
		return addressLine2;
	}
	
	@JsonProperty("numberOfFloors")
	public Integer getNumberOfFloors() {
		return numberOfFloors;
	}
	@JsonProperty("numberOfFloors")
	public void setNumberOfFloors(Integer numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}
	@JsonProperty("businessName")
	public String getBusinessName() {
		return businessName;
	}
	@JsonProperty("businessName")
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	@JsonProperty("billingAddress")
	public String getBillingAddress() {
		return billingAddress;
	}
	@JsonProperty("billingAddress")
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}
	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}
	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@JsonProperty("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	@JsonProperty("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	@JsonProperty("emailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@JsonProperty("callBackPreference")
	public String getCallBackPreference() {
		return callBackPreference;
	}
	@JsonProperty("callBackPreference")
	public void setCallBackPreference(String callBackPreference) {
		this.callBackPreference = callBackPreference;
	}
	@JsonProperty("callBackTiming")
	public String getCallBackTiming() {
		return callBackTiming;
	}
	@JsonProperty("callBackTiming")
	public void setCallBackTiming(String callBackTiming) {
		this.callBackTiming = callBackTiming;
	}
	@JsonProperty("callBackNotes")
	public String getCallBackNotes() {
		return callBackNotes;
	}
	@JsonProperty("callBackNotes")
	public void setCallBackNotes(String callBackNotes) {
		this.callBackNotes = callBackNotes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
