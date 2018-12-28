package com.att.api.nobf.exception;

public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String id) {
        super(String.format("No Order entry found with id: <%s>", id));
    }
}
