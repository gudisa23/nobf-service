package com.att.api.nobf.security.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.api.nobf.security.model.SecurityOrder;
import com.att.api.nobf.security.service.INOBFSecurityOrderService;
import com.bcg.api.common.controller.Controller;

@RestController
@RequestMapping("/api/security")
public class SecurityController extends Controller {

	@Autowired
	private INOBFSecurityOrderService securityOrderService;
	
	@PostMapping(value = "v1/order", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody ResponseEntity<?> submitOrder(@RequestBody @Valid SecurityOrder order, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) return null;
    	return securityOrderService.submitOrder(order);
    }
}
