package com.att.api.nobf.service;

import org.springframework.http.ResponseEntity;

import com.att.api.nobf.dto.Email;

public interface EmailService {

    ResponseEntity<?> sendEmail(Email email);

}
