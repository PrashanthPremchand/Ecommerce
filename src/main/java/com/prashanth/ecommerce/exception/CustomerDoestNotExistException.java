package com.prashanth.ecommerce.exception;

public class CustomerDoestNotExistException extends Exception{
    public CustomerDoestNotExistException(String message){
        super(message);
    }
}
