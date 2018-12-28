package com.att.api.nobf.security.service;

import com.att.api.nobf.security.model.SecurityProduct;

public class SEGEmailDetailsGenerator implements ISecurityEmailDetailsGenerator {

	public String generateEmailTemplateDetails(SecurityProduct product,String internetSpeed) {
		StringBuilder sb = new StringBuilder();
		sb.append("Product: " + product.getSecurityProductType().getProductName() + "\n");
		sb.append("    Number of Users: " + product.getProductConfigurations().getNumberOfUsers() + "\n");
		sb.append("    Service Type: " + product.getProductConfigurations().getServiceType() + "\n");

		return sb.toString();

	}

}
