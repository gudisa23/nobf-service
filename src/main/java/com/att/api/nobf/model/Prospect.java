package com.att.api.nobf.model;

public class Prospect {
	private String firstName;
	private String lastName; 
	private String emailAddress;
	private String phoneNumber;
	private String altPhoneNumber;
	private String bestTimeToCall;
	private String companyName;
	private String companyPhoneNumber;
	private String companyAddress;
	private String companyAddressSecondary;
	private String companyCity;
	private String companyState;
	private String companyZipcode;
	private String comments;
	private String country;
	private String rfiConcern; //references the RFI area where the request came from. i.e. was user needing more phone choices, features, etc.
	
	
	
	public String getAltPhoneNumber() {
		return altPhoneNumber;
	}
	public void setAltPhoneNumber(String altPhoneNumber) {
		this.altPhoneNumber = altPhoneNumber;
	}
	public String getBestTimeToCall() {
		return bestTimeToCall;
	}
	public void setBestTimeToCall(String bestTimeToCall) {
		this.bestTimeToCall = bestTimeToCall;
	}
	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}
	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyAddressSecondary() {
		return companyAddressSecondary;
	}
	public void setCompanyAddressSecondary(String companyAddressSecondary) {
		this.companyAddressSecondary = companyAddressSecondary;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getCompanyState() {
		return companyState;
	}
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	public String getCompanyZipcode() {
		return companyZipcode;
	}
	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRfiConcern() {
		return rfiConcern;
	}
	public void setRfiConcern(String rfiConcern) {
		this.rfiConcern = rfiConcern;
	}
	
}
