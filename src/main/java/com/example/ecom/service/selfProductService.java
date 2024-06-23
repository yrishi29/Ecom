package com.example.ecom.service;

import com.example.ecom.exception.categoryNotFoundException;
import com.example.ecom.model.Category;
import com.example.ecom.model.Product;
import com.example.ecom.repository.categoryRepository;
import com.example.ecom.repository.productRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("selfProductService")
public class selfProductService implements productService{

    private productRepository productRepo;

    private categoryRepository categoryRepo;


    public selfProductService(productRepository productRepo, categoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepo.findById(id.intValue());

    }

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();      //this method now calls up my repo--which is reponsible
                                            // for interacting with DB and returns the List directly
    }

    @Override
    public List<Product> getFetchAndSorted() {
        return List.of();
    }

    @Override
    public Product addProduct(String title, String description, String image, Double price, String category)
            throws categoryNotFoundException {

        Category fetchedCategory = categoryRepo.findByCategoryName(category);
        if (fetchedCategory == null) {
            throw new categoryNotFoundException("Category not found exception");
        }

        Product product = new Product();
        product.setProductPrice(price);
        product.setProductName(title);
        product.setProductImageUrl(image);
        product.setProductDescription(description);
        product.setProductCategory(fetchedCategory);

        return  productRepo.save(product);


    }

    @Override
    public Product deleteProductById(Integer id) {
        return null;
    }
}
