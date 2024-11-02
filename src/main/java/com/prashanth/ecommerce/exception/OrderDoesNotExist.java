package com.prashanth.ecommerce.exception;

public class OrderDoesNotExist extends Exception{
    public OrderDoesNotExist(String message){
        super(message);
    }
}
