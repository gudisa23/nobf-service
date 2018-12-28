package com.att.api.nobf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.api.nobf.dto.Email;
import com.att.api.nobf.dto.RFI;
import com.att.api.nobf.service.INOBFCollaborateService;

@RestController
@RequestMapping("/api")
public class RFIController {

	private static final Logger logger = LoggerFactory.getLogger(RFIController.class);

    private final INOBFCollaborateService service;
    
    @Autowired
    public RFIController(INOBFCollaborateService service) {
        this.service = service;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/v1/rfi/{productType}")
    public ResponseEntity<?> generateRFI(@RequestBody RFI rfi) {
    	//TODO future enrichment based on different product needs for RFI
    	return service.generateRFI(rfi);
    }
}
