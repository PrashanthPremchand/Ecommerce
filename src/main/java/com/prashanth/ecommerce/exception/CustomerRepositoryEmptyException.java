package com.prashanth.ecommerce.exception;

public class CustomerRepositoryEmptyException extends Exception{
    public CustomerRepositoryEmptyException(String message){
        super(message);
    }
}
