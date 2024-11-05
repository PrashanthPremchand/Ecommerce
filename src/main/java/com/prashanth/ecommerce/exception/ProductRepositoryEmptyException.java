package com.prashanth.ecommerce.exception;

public class ProductRepositoryEmptyException extends Exception{
    public ProductRepositoryEmptyException(String message){
        super(message);
    }
}
