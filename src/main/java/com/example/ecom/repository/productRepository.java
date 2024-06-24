package com.example.ecom.repository;

import com.example.ecom.model.Product;
import com.example.ecom.repository.prrojections.getProductTitleandID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface productRepository extends JpaRepository<Product,Integer> {

    Product save(Product p);
    List<Product> findAll();
    //Product findByName(String productName);
    Product findByproductName(String productName);
    Product findById(int id);


    @Query("select p from Product p where p.productID= :id and p.productName = :title ")
    Product getProductByTitleandInteger(@Param("title") String title, @Param("id") Integer id);


    @Query("select p.productName, p.productID from Product p where p.productPrice >= :price")
    List<getProductTitleandID>  findTitleAndIDofAllProductsByPrice(@Param("price") Double price);




}
