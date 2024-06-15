package com.example.ecom.exception;

import org.apache.logging.log4j.message.Message;

public class productNotFoundException extends Exception {

    public productNotFoundException(String message) {
        super(message);
    }


}
