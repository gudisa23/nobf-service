package com.att.api.nobf.security.service;

import org.springframework.http.ResponseEntity;

import com.att.api.nobf.dto.FrontEndResponse;
import com.att.api.nobf.security.model.SecurityOrder;

public interface INOBFSecurityOrderService {

	public ResponseEntity<FrontEndResponse> submitOrder(SecurityOrder order);
}
