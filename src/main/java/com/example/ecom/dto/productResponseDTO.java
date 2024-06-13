package com.example.ecom.dto;

import com.example.ecom.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class productResponseDTO {

    private Integer productID;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productImageUrl;
    private Category prodluctCategory;
}
