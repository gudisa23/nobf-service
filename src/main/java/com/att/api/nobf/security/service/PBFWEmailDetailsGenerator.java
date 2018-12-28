package com.att.api.nobf.security.service;

import com.att.api.nobf.security.model.SecurityProduct;

public class PBFWEmailDetailsGenerator implements ISecurityEmailDetailsGenerator {

	public String generateEmailTemplateDetails(SecurityProduct product,String internetSpeed) {
		StringBuilder sb = new StringBuilder();
		sb.append("Product: " + product.getSecurityProductType().getProductName() + "\n");
		sb.append("    Device: " + product.getProductConfigurations().getDeviceName() + "\n");
		sb.append("    UTM Enabled: " + product.getProductConfigurations().getUtmEnabled() + "\n");
		sb.append("    Consulting Hours: "+product.getProductConfigurations().getConsultingHours()+"\n");
		sb.append("    Installation Method: " + product.getProductConfigurations().getInstallationMethod() + "\n");

		return sb.toString();

	}
}
