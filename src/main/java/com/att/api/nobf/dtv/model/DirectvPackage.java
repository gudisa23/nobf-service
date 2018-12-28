package com.att.api.nobf.dtv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectvPackage extends DirectvProduct {

	@JsonProperty("monthlyPrice")
	Double monthlyPrice;
	@JsonProperty("channelCount")
	Integer channelCount;
	@JsonProperty("colorCodeAccent")
	String colorCodeAccent;
	
	@JsonProperty("colorCodeAccent")
	public String getColorCodeAccent() {
		return colorCodeAccent;
	}
	@JsonProperty("colorCodeAccent")
	public void setColorCodeAccent(String colorCodeAccent) {
		this.colorCodeAccent = colorCodeAccent;
	}
	@JsonProperty("monthlyPrice")
	public Double getMonthlyPrice() {
		return monthlyPrice;
	}
	@JsonProperty("monthlyPrice")
	public void setMonthlyPrice(Double monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}
	@JsonProperty("channelCount")
	public Integer getChannelCount() {
		return channelCount;
	}
	@JsonProperty("channelCount")
	public void setChannelCount(Integer channelCount) {
		this.channelCount = channelCount;
	}
	
	
}
