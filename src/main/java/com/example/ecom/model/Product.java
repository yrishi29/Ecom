package com.example.ecom.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {

    private Integer productID;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productImageUrl;
    private Category productCategory;
}
