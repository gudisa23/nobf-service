package com.att.api.nobf.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.att.api.nobf.dto.Email;
import com.att.api.nobf.dto.RFI;
import com.att.api.nobf.model.CollaborateOrder;
import com.att.api.nobf.model.OrderNotify;

public interface INOBFCollaborateService {

    ResponseEntity<?> createAndSendEmail(Email email);
    
    ResponseEntity<?> generateRFI(RFI rfi);
    
    ResponseEntity<?> generateOrder(CollaborateOrder order);
    
    public List<OrderNotify> retrieveOrders(int daysToInclude, String product);
}
