package com.example.Ewallet.exception;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class EwalletCustomException extends RuntimeException{

	
    private String message=null;
    
    public EwalletCustomException(String message) {
    	super(message);
    	this.message = message;
    }
    
    public EwalletCustomException() {
    	
    }
	
}
