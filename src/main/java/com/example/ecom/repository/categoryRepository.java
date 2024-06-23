package com.example.ecom.repository;

import com.example.ecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface categoryRepository extends JpaRepository<Category, Integer> {

    //Category findByName(String name);

    //Category findByName(String name);

    Category findByCategoryName(String categoryName);



}
