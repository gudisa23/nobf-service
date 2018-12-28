package com.att.api.nobf.dto;

import java.time.Instant;

public class EmailResponse {

    private String emailSentTimestamp;

    public EmailResponse() {
        this.setEmailSentTimestamp(Instant.now().toString());
    }

    public String getEmailSentTimestamp() {
        return emailSentTimestamp;
    }

    public void setEmailSentTimestamp(String emailSentTimestamp) {
        this.emailSentTimestamp = emailSentTimestamp;
    }
}
