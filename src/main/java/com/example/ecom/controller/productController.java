package com.example.ecom.controller;

import com.example.ecom.dto.addProductFakeStoreDTO;
import com.example.ecom.dto.fakestoreResponseDTO;
import com.example.ecom.dto.productResponseDTO;
import com.example.ecom.model.Product;
import com.example.ecom.service.productService;
import org.springframework.web.bind.annotation.*;


@RestController
public class productController {

    private productService pService;

    public productController( productService pService) {
        this.pService = pService;

    }

    @GetMapping("/product/{id}")
    public productResponseDTO getProductById(@PathVariable ("id") int id) {

        //need to do conversion here from Product to DTO
        Product product = pService.getProductById(id);
        productResponseDTO productResponseDTO = new productResponseDTO();

        productResponseDTO = convertProductToResponseDTO(product);
        return productResponseDTO;
    }



    private productResponseDTO convertProductToResponseDTO(Product product) {

        productResponseDTO productResponseDTO = new productResponseDTO();

        productResponseDTO.setProductID(product.getProductID());
        productResponseDTO.setProductName(product.getProductName());
        productResponseDTO.setProductDescription(product.getProductDescription());
        productResponseDTO.setProductPrice(product.getProductPrice());
        productResponseDTO.setProductImageUrl(product.getProductImageUrl());
        productResponseDTO.setProdluctCategory(product.getProductCategory());

        return productResponseDTO;

    }

    @PostMapping("/addProduct")
    public Product createProduct (@RequestBody addProductFakeStoreDTO dto ){

        Product p = pService.addProduct(
                dto.getTitle(),
                dto.getDescription(),
                dto.getImage(),
                dto.getPrice());

        return p;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public Product deleteProduct(@PathVariable ("id") int id){
        return pService.deleteProductById(id);

    }
}