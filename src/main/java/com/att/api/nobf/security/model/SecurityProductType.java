package com.att.api.nobf.security.model;

public enum SecurityProductType {

	PBFW("Premises Based Firewall"),CWSS("Cloud Web Security Services"),DDOS("Reactive Distributed Denial of Service"),
	SEG("Secure Email Gateway"),CR("Cybersecurity Report");
	
	private final String productName;

	private SecurityProductType(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return productName;
	}
}
