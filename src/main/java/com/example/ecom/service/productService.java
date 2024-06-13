package com.example.ecom.service;

import com.example.ecom.model.Product;
import org.apache.logging.log4j.message.StringFormattedMessage;

public interface productService {

    Product getProductById (Integer id );
    Product getAllProduct();
    Product addProduct(String title, String description, String image, Double price);
    Product  deleteProductById(Integer id);

}



