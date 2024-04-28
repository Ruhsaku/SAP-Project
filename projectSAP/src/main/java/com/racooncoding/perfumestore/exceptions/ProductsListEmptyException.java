package com.racooncoding.perfumestore.exceptions;

public class ProductsListEmptyException extends RuntimeException{
    public ProductsListEmptyException(){
        super("List is empty");
    }
}
