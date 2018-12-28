package com.att.api.nobf.entities;

import org.junit.Test;

import com.att.api.nobf.model.Products;

public class ProductsTest {

	private Products products;
	
	@Test
	public void testValidStringSetsProduct() {
		products = Products.fromString("adi");
		assert(Products.ADI.equals(products));
	}
	
	@Test
	public void toStringReturnsString() {
		products = Products.fromString("directv");
		assert("directv".equals(products.toString()));
	}
	
}
