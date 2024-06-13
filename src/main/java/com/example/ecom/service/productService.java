package com.example.ecom.service;

import com.example.ecom.model.Product;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.util.List;

public interface productService {

    Product getProductById (Integer id );
    List<Product> getAllProduct();
    List<Product> getFetchAndSorted();
    Product addProduct(String title, String description, String image, Double price);
    Product  deleteProductById(Integer id);

}



