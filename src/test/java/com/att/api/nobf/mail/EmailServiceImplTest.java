package com.att.api.nobf.mail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.att.api.nobf.dto.Email;
import com.att.api.nobf.service.EmailService;
import com.att.api.nobf.service.EmailServiceImpl;
import com.att.api.nobf.utility.Errors;
import com.att.api.nobf.utility.ServiceConstants;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    EmailService emailService = new EmailServiceImpl();

    @Mock
    JavaMailSender javaMailSender;

    MimeMessage testMimeMessage;

    @Before
    public void setup() {
        JavaMailSender jms = new JavaMailSenderImpl();
        testMimeMessage = jms.createMimeMessage();
    }

    @Test
    public void testSuccessReturnsEmailResponse() {
        given(javaMailSender.createMimeMessage()).willReturn(testMimeMessage);
        doNothing().when(javaMailSender).send(isA(MimeMessage.class));
        ResponseEntity<?> response = emailService.sendEmail(getValidEmailData());
        assert(HttpStatus.OK.equals(response.getStatusCode()));
        assertNotNull(response.getBody());
    }

    private Email getValidEmailData() {
        Email email = new Email();
        email.setSubject("Subject");
        email.setText("TEXT");
        email.setTo("joe@notanemail.com");
        return email;
    }

    @Test
    public void testNoInputReturnsBadRequest() {
        given(javaMailSender.createMimeMessage()).willReturn(testMimeMessage);
        ResponseEntity<?> response = emailService.sendEmail(null);
        assert(HttpStatus.BAD_REQUEST.equals(response.getStatusCode()));
        assert(ServiceConstants.USER_ERROR_RESPONSES.get(ServiceConstants.EMAIL_BAD_DATA_ERROR).equals(((Errors)response.getBody()).getUserMessage()));
    }

    @Test
    public void testMailServerFailureReturnsInternalError() {
        given(javaMailSender.createMimeMessage()).willReturn(testMimeMessage);
        doThrow(new MailSendException("Oops")).when(javaMailSender).send(isA(MimeMessage.class));
        ResponseEntity<?> response = emailService.sendEmail(getValidEmailData());
        assert(HttpStatus.INTERNAL_SERVER_ERROR.equals(response.getStatusCode()));
        assert(ServiceConstants.USER_ERROR_RESPONSES.get(ServiceConstants.EMAIL_SEND_ERROR).equals(((Errors)response.getBody()).getUserMessage()));
    }

}
