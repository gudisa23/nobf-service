package com.att.api.nobf.security.service;

import com.att.api.nobf.security.model.SecurityProductType;

public class SecurityEmailDetailsGeneratorFactory {

	public ISecurityEmailDetailsGenerator getSecurityEmailDetailsGenerator(SecurityProductType productType) {
		switch(productType) {
		case CR:
			return new CREmailDetailsGenerator();
		case CWSS:
			return new CWSSEmailDetailsGenerator();
		case PBFW:
			return new PBFWEmailDetailsGenerator();
		case SEG:
			return new SEGEmailDetailsGenerator();
		case DDOS:
			return new DDOSEmailDetailsGenerator();
		default:
			return null;
		}
	}
}
