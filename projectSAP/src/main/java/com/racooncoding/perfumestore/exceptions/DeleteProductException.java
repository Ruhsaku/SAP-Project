package com.racooncoding.perfumestore.exceptions;

public class DeleteProductException extends RuntimeException {
    public DeleteProductException() {
        super("Product with this id does not exist");
    }

    public DeleteProductException(String message) {
        super(message);
    }
}
