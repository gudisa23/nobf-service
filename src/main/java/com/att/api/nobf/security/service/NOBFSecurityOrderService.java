package com.att.api.nobf.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.att.api.nobf.db.DBService;
import com.att.api.nobf.dto.FrontEndResponse;
import com.att.api.nobf.dtv.DTVService;
import com.att.api.nobf.model.OrderType;
import com.att.api.nobf.security.model.SecurityOrder;
import com.att.api.nobf.service.BaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NOBFSecurityOrderService extends BaseService implements INOBFSecurityOrderService{
	
	@Autowired
	private ISecurityEmailService securityEmailService;
	
	@Autowired
	private DBService dbService;
	
	private static final Logger logger = LoggerFactory.getLogger(NOBFSecurityOrderService.class);

	public ResponseEntity<FrontEndResponse> submitOrder(SecurityOrder order) {
		StringBuilder responseMessage = new StringBuilder();
		
		//Attempt to write order details to database, log on failure
		String orderID = dbService.saveSecurityOrder(order);
		if (null == orderID) {
			responseMessage.append("WARNING - Failed to write Security product order to database.\n");
			try {
				ObjectMapper mapper = new ObjectMapper();
				logger.warn("Failed to write Security product order to database: " + mapper.writeValueAsString(order));
			} catch (JsonProcessingException e) {
				logger.warn("Exception while logging database error: " + e.getLocalizedMessage());
			}			
		}
		
		//Attempt to send order details + database ID to CSR, error on failure
		String emailBody = securityEmailService.emailSecurityOrder(order);
		if (null == emailBody) {
			responseMessage.append("ERROR - Failed to send Security product order email for order ID "+orderID+".");
			logger.warn("Failed to send Security product order email for order ID "+orderID);
			return ResponseEntity.ok(new FrontEndResponse(FrontEndResponse.ERROR_CODE, orderID, responseMessage.toString()));
		}
		responseMessage.append("Security product order ID "+ orderID + " was successfully emailed.\n");
		
		//Attempt to store email sent in database, log on failure
		boolean emailStorageSuccessful = dbService.saveOrderNotify(order, "Security", emailBody, orderID);
		if(!emailStorageSuccessful) {
			responseMessage.append("WARNING - Failed to write Security product order email for order ID "+orderID+" to database.");
			logger.warn("Failed to write Security product order email for order ID "+orderID+" to database.");
		}
		return ResponseEntity.ok(new FrontEndResponse(FrontEndResponse.SUCCESS_CODE, orderID, responseMessage.toString()));
	}

}
