package com.matteusmoreno.sendix.exception;

public class ProductAlreadyDisabledException extends RuntimeException {
    public ProductAlreadyDisabledException(String message) {
        super(message);
    }
}
