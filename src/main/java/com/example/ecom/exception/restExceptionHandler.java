package com.example.ecom.exception;

import com.example.ecom.dto.errorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class restExceptionHandler {

    @ExceptionHandler(productNotFoundException.class)
    public ResponseEntity handleNoProductFound(){

        errorDTO edto = new errorDTO(400, "No Prodcut Found", new Date());

        return  new ResponseEntity<errorDTO>(edto,HttpStatus.BAD_REQUEST);

    }

}
