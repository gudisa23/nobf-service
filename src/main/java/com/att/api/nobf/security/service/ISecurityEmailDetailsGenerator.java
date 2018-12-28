package com.att.api.nobf.security.service;

import com.att.api.nobf.security.model.SecurityProduct;
public interface ISecurityEmailDetailsGenerator {

	 public String generateEmailTemplateDetails(SecurityProduct securityProduct,String internetSpeed);

}
