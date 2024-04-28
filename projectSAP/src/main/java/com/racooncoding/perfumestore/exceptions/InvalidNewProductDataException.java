package com.racooncoding.perfumestore.exceptions;

public class InvalidNewProductDataException extends RuntimeException{
    public InvalidNewProductDataException(){
        super("Product addition failed. Try again.");
    }
    public InvalidNewProductDataException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return "Product addition failed. Try again.";
    }
}
