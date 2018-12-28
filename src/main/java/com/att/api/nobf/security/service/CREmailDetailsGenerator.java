package com.att.api.nobf.security.service;

import com.att.api.nobf.security.model.SecurityProduct;

public class CREmailDetailsGenerator implements ISecurityEmailDetailsGenerator {

	public String generateEmailTemplateDetails(SecurityProduct product,String internetSpeed) {
		StringBuilder sb = new StringBuilder();
		sb.append("Product: " + product.getSecurityProductType().getProductName() + "\n");

		return sb.toString();

	}

}
