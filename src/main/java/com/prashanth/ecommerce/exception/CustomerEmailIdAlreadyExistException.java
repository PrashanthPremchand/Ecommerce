package com.prashanth.ecommerce.exception;

public class CustomerEmailIdAlreadyExistException extends Exception{
    public CustomerEmailIdAlreadyExistException(String message){
        super(message);
    }
}
