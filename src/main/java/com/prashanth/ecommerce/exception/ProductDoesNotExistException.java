package com.prashanth.ecommerce.exception;

public class ProductDoesNotExistException extends Exception{
    public ProductDoesNotExistException(String message){
        super(message);
    }
}
