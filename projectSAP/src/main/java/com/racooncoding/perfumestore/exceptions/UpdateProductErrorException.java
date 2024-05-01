package com.racooncoding.perfumestore.exceptions;

public class UpdateProductErrorException extends Exception {
    public UpdateProductErrorException() {
        super("Could not update product");
    }

    public UpdateProductErrorException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getMessage(String m) {
        return m;
    }
}
