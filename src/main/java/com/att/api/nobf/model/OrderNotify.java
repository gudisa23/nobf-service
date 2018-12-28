package com.att.api.nobf.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderNotify {
	String orderType;
	boolean emailSent;
	Date createDate;
	Date lastModifiedDate;
	Object data; //will store any type of order that will be emailed
	String htmlText;
	String referenceOrderID; //refers to order id of the data being stored, i.e. collaborate order id
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public boolean isEmailSent() {
		return emailSent;
	}
	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getHtmlText() {
		return htmlText;
	}
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	public String getReferenceOrderID() {
		return referenceOrderID;
	}
	public void setReferenceOrderID(String referenceOrderID) {
		this.referenceOrderID = referenceOrderID;
	}
	
	

}
