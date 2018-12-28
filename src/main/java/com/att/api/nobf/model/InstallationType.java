package com.att.api.nobf.model;

public enum InstallationType {
	
	ATT("AT&T Installation"), CUSTOMER("Customer Installation");
	
	private final String name;

	private InstallationType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
