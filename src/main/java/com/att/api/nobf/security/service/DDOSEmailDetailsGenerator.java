package com.att.api.nobf.security.service;

import com.att.api.nobf.security.constants.SecurityConstants;
import com.att.api.nobf.security.model.SecurityProduct;

public class DDOSEmailDetailsGenerator implements ISecurityEmailDetailsGenerator {

	public String generateEmailTemplateDetails(SecurityProduct product, String internetSpeed) {
		StringBuilder sb = new StringBuilder();
		sb.append("Product: " + product.getSecurityProductType().getProductName() + "\n");

		if (SecurityConstants.INTERNET_SPEED_RANGES.get(0).equals(internetSpeed)) {
			sb.append("    Bandwith Tier 1: 1\n");
		} else if (SecurityConstants.INTERNET_SPEED_RANGES.contains(internetSpeed)){
			sb.append("    Bandwith Tier 2: 1\n");
		}

		return sb.toString();

	}
}
