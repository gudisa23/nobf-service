package com.att.api.nobf.security.service;

import com.att.api.nobf.security.model.SecurityOrder;

public interface ISecurityEmailService {
	
   public String emailSecurityOrder(SecurityOrder order);
}
