/**
 * Copyright (2012-2014) TMX Finance, Inc.
 */
package com.att.api.nobf.exception;

/**
 * @author marc.archin
 *
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = -8895182446166795196L;

    public ServiceException() {
        super();
    }

    public ServiceException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final Throwable cause) {
        super(cause);
    }

}
