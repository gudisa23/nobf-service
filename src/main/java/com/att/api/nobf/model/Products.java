package com.att.api.nobf.model;

public enum Products {
	DIRECTV("directv"), ADI("adi"), COLLABORATE("collaborate"), SECURITY("security"), WIRELESS("wireless"), DEFAULT("");
	
	private String productName;
	private Products(String pName) {
		this.productName=pName.toLowerCase();
	}
	
	public static Products fromString(String productName) {
		for (Products p: Products.values()) {
			if (p.productName.equals(productName.toLowerCase())) {
				return p;
			}
		}
		return Products.DEFAULT;
	}
	
	@Override
	public String toString() {
		return productName;
	}
}
