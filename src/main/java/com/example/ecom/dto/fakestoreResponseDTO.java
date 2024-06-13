package com.example.ecom.dto;

import com.example.ecom.model.Category;
import com.example.ecom.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class fakestoreResponseDTO {

    private Integer id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;

    public Product toProduct(){

        Product p = new Product();
        p.setProductID(id);
        p.setProductName(title);
        p.setProductDescription(description);
        p.setProductPrice(Double.valueOf(price));
        p.setProductImageUrl(image);


        Category c = new Category();
        c.setCategoryName(category);
        p.setProductCategory(c);

        return p;
    }
}
