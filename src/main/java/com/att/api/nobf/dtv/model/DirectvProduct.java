package com.att.api.nobf.dtv.model;

import javax.validation.constraints.Pattern;

import com.att.api.nobf.utility.ServiceConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class DirectvProduct {

	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("id")
	String productKey;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("referenceName")
	String lookupName;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("name")
	String displayName;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("description")
	String displayDescription;
	@Pattern(regexp = ServiceConstants.ALLOWED_STRING_PATTERN)
	@JsonProperty("imgRef")
	String imageRefURL;
	
	@JsonProperty("id")
	public String getProductKey() {
		return productKey;
	}
	@JsonProperty("id")
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	@JsonProperty("name")
	public String getDisplayName() {
		return displayName;
	}
	@JsonProperty("name")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@JsonProperty("description")
	public String getDisplayDescription() {
		return displayDescription;
	}
	@JsonProperty("description")
	public void setDisplayDescription(String displayDescription) {
		this.displayDescription = displayDescription;
	}
	@JsonProperty("imgRef")
	public String getImageRefURL() {
		return imageRefURL;
	}
	@JsonProperty("imgRef")
	public void setImageRefURL(String imageRefURL) {
		this.imageRefURL = imageRefURL;
	}
	@JsonProperty("referenceName")
	public String getLookupName() {
		return lookupName;
	}
	@JsonProperty("referenceName")
	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}
	
}
