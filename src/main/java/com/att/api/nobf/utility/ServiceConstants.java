package com.att.api.nobf.utility;

import java.util.HashMap;
import java.util.Map;

public class ServiceConstants {

    public static final String EMAIL_FROM_ADDRESS = "quickstriketest@gmail.com";
    public static final String ALLOWED_STRING_PATTERN = "^[A-Za-z0-9 _\\-@!#\\$,\\.&?]*$";
    // ------------- Error Constants -------------------
    public static final int EMAIL_UNKNOWN_ERROR = 0;
    public static final int EMAIL_BAD_DATA_ERROR = 1;
    public static final int EMAIL_ATTACHMENT_ERROR = 2;
    public static final int EMAIL_SEND_ERROR = 3;

    public static final Map<Integer, String> DEVELOPER_ERROR_RESPONSES = new HashMap<>();
    static {
        DEVELOPER_ERROR_RESPONSES.put(0, "Unknown error when creating new email request: %s");
        DEVELOPER_ERROR_RESPONSES.put(1, "Invalid data caused error when populating email request: %s");
        DEVELOPER_ERROR_RESPONSES.put(2, "Error when attaching file to email request: %s");
        DEVELOPER_ERROR_RESPONSES.put(3, "Error when sending email to mail server: %s");
    }
    public static final Map<Integer, String> USER_ERROR_RESPONSES = new HashMap<>();
    static {
        USER_ERROR_RESPONSES.put(0, "Unknown error when creating new email request");
        USER_ERROR_RESPONSES.put(1, "Invalid data prevented email from being generated");
        USER_ERROR_RESPONSES.put(2, "Unknown issue attaching file to email request");
        USER_ERROR_RESPONSES.put(3, "Error when attempting to deliver email");
    }
}
