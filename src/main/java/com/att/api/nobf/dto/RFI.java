package com.att.api.nobf.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.att.api.nobf.model.Prospect;

@Document(collection ="requestinformation")
public class RFI {
	private Prospect prospect;
	private String productInterest;
	@Id
	private String id;
	private Date orderCreateDate;
	
	public RFI() {
		orderCreateDate = new Date();
	}

	public Prospect getProspect() {
		return prospect;
	}

	public void setProspect(Prospect prospect) {
		this.prospect = prospect;
	}

	public String getProductInterest() {
		return productInterest;
	}

	public void setProductInterest(String productInterest) {
		this.productInterest = productInterest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}
}
