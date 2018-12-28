package com.att.api.nobf.exception;

public class ItemNotFoundException extends Exception {
    private static final long serialVersionUID = 5672136543562784022L;

    public ItemNotFoundException() {
    }

    public ItemNotFoundException(final String message) {
        super(message);
    }

    public ItemNotFoundException(final Throwable cause) {
        super(cause);
    }

    public ItemNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
