package com.example.ecom.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Product extends Base {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private Integer productID;

    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productImageUrl;

   @ManyToOne(cascade = CascadeType.REMOVE)  //left side is product and right side is category
    private Category productCategory;
}
