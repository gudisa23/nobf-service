package com.att.api.nobf.mail;

import org.junit.Test;

import com.att.api.nobf.dto.EmailResponse;

import static org.junit.Assert.assertNotNull;

public class EmailResponseTest {

    @Test
    public void testConstructorSetsTimestamp() {
        assertNotNull((new EmailResponse().getEmailSentTimestamp()));
    }
}
