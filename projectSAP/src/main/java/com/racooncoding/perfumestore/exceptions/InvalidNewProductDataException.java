package com.racooncoding.perfumestore.exceptions;

public class InvalidNewProductDataException extends RuntimeException{
    public InvalidNewProductDataException(){
        super("Product addition failed. Try again, my friend.");
    }
    public InvalidNewProductDataException(String message){
        super(message);
    }


}
