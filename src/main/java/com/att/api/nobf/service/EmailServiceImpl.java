package com.att.api.nobf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.att.api.nobf.dto.Email;
import com.att.api.nobf.dto.EmailResponse;
import com.att.api.nobf.utility.Errors;
import com.att.api.nobf.utility.ServiceConstants;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public ResponseEntity<?> sendEmail(Email email) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
        } catch (MessagingException e) {
            logger.warn(String.format("Unable to create new email message: %s", e));
            return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ServiceConstants.EMAIL_UNKNOWN_ERROR, e.getMessage());
        }

        try {
            helper.setFrom(ServiceConstants.EMAIL_FROM_ADDRESS);
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(), true);
        } catch (NullPointerException | MessagingException e) {
            logger.warn(String.format("Unable to populate email: %s", e));
            return generateErrorResponse(HttpStatus.BAD_REQUEST, ServiceConstants.EMAIL_BAD_DATA_ERROR, e.getMessage());
        }

        if (null != email.getFile()) {
            try {
                //TODO update to do files better in future
                helper.addAttachment("fileName.pdf", email.getFile());
            } catch (MessagingException e) {
                logger.warn(String.format("Unable to add attachment to email: %s", e));
                return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ServiceConstants.EMAIL_ATTACHMENT_ERROR, e.getMessage());
            }
        }
        try {
            emailSender.send(message);
        } catch (MailException e) {
            logger.warn(String.format("Unable to send email to mail server: %s", e));
            return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ServiceConstants.EMAIL_SEND_ERROR, e.getMessage());
        }

        return ResponseEntity.ok(new EmailResponse());
    }

    private ResponseEntity<Errors> generateErrorResponse(HttpStatus status, int errorCode, String exceptionMessage) {
        Errors responseError = new Errors();
        responseError.setStatus(status.value());
        responseError.setDeveloperMessage(String.format(ServiceConstants.DEVELOPER_ERROR_RESPONSES.get(errorCode), exceptionMessage));
        responseError.setUserMessage(ServiceConstants.USER_ERROR_RESPONSES.get(errorCode));
        return ResponseEntity.status(status).body(responseError);
    }
}
