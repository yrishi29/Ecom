package com.example.ecom.repository;

import com.example.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface productRepository extends JpaRepository<Product,Integer> {

    Product save(Product p);
    List<Product> findAll();
    //Product findByName(String productName);
    Product findByproductName(String productName);
    Product findById(int id);

}
