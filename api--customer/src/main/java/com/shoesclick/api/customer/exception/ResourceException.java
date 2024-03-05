package com.shoesclick.api.customer.exception;

public abstract class ResourceException extends RuntimeException {

    private static final long serialVersionUID = 5855841161576015514L;

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceException(String message) {
        super(message);
    }

}