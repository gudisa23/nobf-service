package com.att.api.nobf.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.att.api.nobf.dto.Email;
import com.att.api.nobf.dto.RFI;
import com.att.api.nobf.model.CollaborateOrder;
import com.att.api.nobf.model.OrderNotify;

@Service
public class NOBFCollaborateService implements INOBFCollaborateService {

    private static final Logger logger = LoggerFactory.getLogger(NOBFCollaborateService.class);

    @Autowired
    private EmailService emailService;
    
    
    @Autowired
    private RFIService rfiService;

    @Autowired
    private CollaborateOrderService coService;
    
    public ResponseEntity<?> createAndSendEmail(Email email) {
        return emailService.sendEmail(email);
    }

	@Override
	public ResponseEntity<?> generateRFI(RFI rfi) {
		return rfiService.generateRFI(rfi);
	}
	
	public  ResponseEntity<?> generateOrder(CollaborateOrder order) {
		return coService.generateOrder(order);
	}
	
	public List<OrderNotify>  retrieveOrders(int daysToInclude, String product) {
		return coService.retrieveOrders(daysToInclude, product);
	}
}
