package com.att.api.nobf.controller;

import com.att.api.nobf.dto.Email;
import com.att.api.nobf.service.INOBFCollaborateService;
import com.bcg.api.common.controller.Controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class EmailController extends Controller{

    private final INOBFCollaborateService service;

    @Autowired
    public EmailController(INOBFCollaborateService service) {
        this.service = service;
    }

    @PostMapping("/v1/emails")
    public ResponseEntity<?> email(@RequestBody Email email) {
        return service.createAndSendEmail(email);
    }

    //TODO Add API for /order/email
}
