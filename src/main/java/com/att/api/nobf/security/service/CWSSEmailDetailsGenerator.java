package com.att.api.nobf.security.service;

import com.att.api.nobf.security.model.SecurityProduct;

public class CWSSEmailDetailsGenerator implements ISecurityEmailDetailsGenerator {

	public String generateEmailTemplateDetails(SecurityProduct product,String internetSpeed) {
		StringBuilder sb = new StringBuilder();
		sb.append("Product: " + product.getSecurityProductType().getProductName() + "\n");
		sb.append("    Number of Users: " + product.getProductConfigurations().getNumberOfUsers() + "\n");

		return sb.toString();

	}

}
