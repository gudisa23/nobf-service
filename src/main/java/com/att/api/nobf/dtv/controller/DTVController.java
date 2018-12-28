package com.att.api.nobf.dtv.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.api.nobf.dto.FrontEndResponse;
import com.att.api.nobf.dtv.DTVService;
import com.att.api.nobf.dtv.model.BusinessCustomer;
import com.att.api.nobf.dtv.model.DirectvOrder;
import com.bcg.api.common.controller.Controller;
import com.directv.ei.schemas.wsdl.propertyservices.v3_1.requestscrubaddress.RequestScrubAddressResponseEntity;
import com.directv.ei.schemas.wsdl.propertyservices.v3_1.requestscrubaddress.RequestScrubAddressRequestEntity.Address;

@RestController
@RequestMapping("/api/dtv")
public class DTVController extends Controller {

	@Autowired
	private DTVService dtvService;
	
	@CrossOrigin(origins="*")
    @PostMapping(value = "v1/order", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody ResponseEntity<?> submitOrder(@RequestBody DirectvOrder order) {
    	return dtvService.submitOrder(order);
    }
    
	@CrossOrigin(origins="*")
    @PostMapping(value = "v1/rfi", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody ResponseEntity<?> submitRFI(@RequestBody BusinessCustomer dtvCustomer) {
    	return dtvService.submitRFI(dtvCustomer);
    }
}
