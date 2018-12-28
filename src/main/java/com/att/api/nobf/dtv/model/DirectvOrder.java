package com.att.api.nobf.dtv.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection ="dtvorder")
public class DirectvOrder {

	@Id
	private String id;
	private String timeStamp;
	
	@JsonProperty("businessCustomer")
	private BusinessCustomer businessCustomer;
	@JsonProperty("offerFilter")
	private OfferFilter offerFilter;
	@JsonProperty("selectedPackage")
	private DirectvPackage selectedPackage;
	@JsonProperty("selectedAddons")
	private List<DirectvAddon> selectedAddons;
	@JsonProperty("selectedEquipment")
	private List<DirectvEquipment> selectedEquipment;
	@JsonProperty("applicablePromotions")
	private List<DirectvPromotion> applicablePromotions;
	@JsonProperty("firstBillQuotedPrice")
	private Double firstBillQuotedPrice;
	@JsonProperty("secondBillQuotedPrice")
	private Double secondBillQuotedPrice;
	
	@JsonProperty("firstBillQuotedPrice")
	public Double getFirstBillQuotedPrice() {
		return firstBillQuotedPrice;
	}
	@JsonProperty("firstBillQuotedPrice")
	public void setFirstBillQuotedPrice(Double firstBillQuotedPrice) {
		this.firstBillQuotedPrice = firstBillQuotedPrice;
	}
	@JsonProperty("secondBillQuotedPrice")
	public Double getSecondBillQuotedPrice() {
		return secondBillQuotedPrice;
	}
	@JsonProperty("secondBillQuotedPrice")
	public void setSecondBillQuotedPrice(Double secondBillQuotedPrice) {
		this.secondBillQuotedPrice = secondBillQuotedPrice;
	}
	@JsonProperty("businessCustomer")
	public BusinessCustomer getBusinessCustomer() {
		return businessCustomer;
	}
	@JsonProperty("businessCustomer")
	public void setBusinessCustomer(BusinessCustomer businessCustomer) {
		this.businessCustomer = businessCustomer;
	}
	@JsonProperty("offerFilter")
	public OfferFilter getOfferFilter() {
		return offerFilter;
	}
	@JsonProperty("offerFilter")
	public void setOfferFilter(OfferFilter offerFilter) {
		this.offerFilter = offerFilter;
	}
	@JsonProperty("selectedPackage")
	public DirectvPackage getSelectedPackage() {
		return selectedPackage;
	}
	@JsonProperty("selectedPackage")
	public void setSelectedPackage(DirectvPackage selectedPackage) {
		this.selectedPackage = selectedPackage;
	}
	@JsonProperty("selectedAddons")
	public List<DirectvAddon> getSelectedAddons() {
		return selectedAddons;
	}
	@JsonProperty("selectedAddons")
	public void setSelectedAddons(List<DirectvAddon> selectedAddons) {
		this.selectedAddons = selectedAddons;
	}
	@JsonProperty("selectedEquipment")
	public List<DirectvEquipment> getSelectedEquipment() {
		return selectedEquipment;
	}
	@JsonProperty("selectedEquipment")
	public void setSelectedEquipment(List<DirectvEquipment> selectedEquipment) {
		this.selectedEquipment = selectedEquipment;
	}
	@JsonProperty("applicablePromotions")
	public List<DirectvPromotion> getApplicablePromotions() {
		return applicablePromotions;
	}
	@JsonProperty("applicablePromotions")
	public void setApplicablePromotions(List<DirectvPromotion> applicablePromotions) {
		this.applicablePromotions = applicablePromotions;
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
