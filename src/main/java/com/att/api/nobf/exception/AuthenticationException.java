/**
 * Copyright (2012-2014) TMX Finance, Inc.
 */
package com.att.api.nobf.exception;

/**
 * @author marc.archin
 *
 */
public class AuthenticationException extends Exception {
    private static final long serialVersionUID = 3090834591994208669L;

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(final String message) {
        super(message);
    }

    public AuthenticationException(final Throwable cause) {
        super(cause);
    }

}
