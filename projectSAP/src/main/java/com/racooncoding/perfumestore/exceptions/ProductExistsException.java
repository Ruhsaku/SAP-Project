package com.racooncoding.perfumestore.exceptions;

public class ProductExistsException extends RuntimeException{
    public ProductExistsException(){
        super("Product already exists.");
    }
    public ProductExistsException(String message){
        super(message);
    }
}
