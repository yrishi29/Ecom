package com.example.ecom.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class errorDTO {

    private Integer error;
    private String message;
    private Date timestamp;

    public errorDTO(int i, String noProdcutFound, Date date) {
        this.error = i;
        this.message = noProdcutFound;
        this.timestamp = date;
    }
}
