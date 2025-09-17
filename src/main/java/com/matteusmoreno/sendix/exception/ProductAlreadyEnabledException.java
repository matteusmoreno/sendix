package com.matteusmoreno.sendix.exception;

public class ProductAlreadyEnabledException extends RuntimeException {
    public ProductAlreadyEnabledException(String message) {
        super(message);
    }
}
