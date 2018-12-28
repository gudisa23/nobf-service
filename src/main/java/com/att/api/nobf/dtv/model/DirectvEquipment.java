package com.att.api.nobf.dtv.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectvEquipment extends DirectvProduct {

	@JsonProperty("upfrontCost")
	Double initialCost;
	@JsonProperty("monthlyCostPerRecevier")
	Double monthlyCostPerRecevier;
	@JsonProperty("flatFee")
	Double flatFee;
	@JsonProperty("scalingMonthlyCostPerReceiver")
	Map<Integer, Double> scalingMonthlyCostPerReceiver;
	@JsonProperty("productCount")
	Integer totalPurchased;
	
	@JsonProperty("productCount")
	public Integer getTotalPurchased() {
		return totalPurchased;
	}
	@JsonProperty("productCount")
	public void setTotalPurchased(Integer totalPurchased) {
		this.totalPurchased = totalPurchased;
	}
	@JsonProperty("upfrontCost")
	public Double getInitialCost() {
		return initialCost;
	}
	@JsonProperty("upfrontCost")
	public void setInitialCost(Double initialCost) {
		this.initialCost = initialCost;
	}
	@JsonProperty("monthlyCostPerRecevier")
	public Double getMonthlyCostPerRecevier() {
		return monthlyCostPerRecevier;
	}
	@JsonProperty("monthlyCostPerRecevier")
	public void setMonthlyCostPerRecevier(Double monthlyCostPerRecevier) {
		this.monthlyCostPerRecevier = monthlyCostPerRecevier;
	}
	@JsonProperty("flatFee")
	public Double getFlatFee() {
		return flatFee;
	}
	@JsonProperty("flatFee")
	public void setFlatFee(Double flatFee) {
		this.flatFee = flatFee;
	}
	@JsonProperty("scalingMonthlyCostPerReceiver")
	public Map<Integer, Double> getScalingMonthlyCostPerReceiver() {
		return scalingMonthlyCostPerReceiver;
	}
	@JsonProperty("scalingMonthlyCostPerReceiver")
	public void setScalingMonthlyCostPerReceiver(Map<Integer, Double> scalingMonthlyCostPerReceiver) {
		this.scalingMonthlyCostPerReceiver = scalingMonthlyCostPerReceiver;
	}
	
	
}
