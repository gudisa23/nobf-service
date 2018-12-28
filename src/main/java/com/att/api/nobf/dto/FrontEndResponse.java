package com.att.api.nobf.dto;

import java.time.Instant;

public class FrontEndResponse {

	public static final String SUCCESS_CODE = "success";
	public static final String ERROR_CODE = "error";
	
	private String responseTimeStamp;
	private String message;
	private String successOrErrorCode;
	private String orderID;
	
	
	public FrontEndResponse() {
		setResponseTimeStamp(Instant.now().toString());
	}
	
	public FrontEndResponse(String successOrErrorCode, String orderID, String message) {
		setResponseTimeStamp(Instant.now().toString());
		setSuccessOrErrorCode(successOrErrorCode);
		setOrderID(orderID);
		setMessage(message);
	}
	
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getResponseTimeStamp() {
		return responseTimeStamp;
	}
	public void setResponseTimeStamp(String responseTimeStamp) {
		this.responseTimeStamp = responseTimeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSuccessOrErrorCode() {
		return successOrErrorCode;
	}
	public void setSuccessOrErrorCode(String successOrErrorCode) {
		this.successOrErrorCode = successOrErrorCode;
	}
	
	public void setSuccessOrErrorCodeToSuccess() {
		this.successOrErrorCode = SUCCESS_CODE;
	}
	
	public void setSuccessOrErrorCodeToError() {
		this.successOrErrorCode = ERROR_CODE;
	}
}
