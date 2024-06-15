package com.example.ecom.controller;

import com.example.ecom.configration.productControllerComparator;
import com.example.ecom.dto.addProductFakeStoreDTO;
import com.example.ecom.dto.fakestoreResponseDTO;
import com.example.ecom.dto.productResponseDTO;
import com.example.ecom.exception.productNotFoundException;
import com.example.ecom.model.Product;
import com.example.ecom.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@RestController
public class productController {

    private productService pService;
    private productControllerComparator pcc;

    public productController(productService pService, productControllerComparator pcc) {
        this.pService = pService;
        this.pcc = pcc;
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<productResponseDTO > getProductById(@PathVariable("id") int id) throws productNotFoundException {

        //need to do conversion here from Product to DTO
        Product product = pService.getProductById(id);
        productResponseDTO productDTO;

        if(product==null) {
            throw new productNotFoundException("Some error Occured here");

//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        productDTO = convertProductToResponseDTO(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);


    }


    @PostMapping("/addProduct")
    public Product createProduct(@RequestBody addProductFakeStoreDTO dto) {

        Product p = pService.addProduct(
                dto.getTitle(),
                dto.getDescription(),
                dto.getImage(),
                dto.getPrice());

        return p;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public Product deleteProduct(@PathVariable("id") int id) {
        return pService.deleteProductById(id);

    }


    @GetMapping("/allsortedProduct")

    public List<productResponseDTO> AllSortedProduct(@RequestParam(defaultValue = "id") String sortBy) {

        List<productResponseDTO> listOfProductResponseDTO = new ArrayList<>();
        List<Product> listOfProduct = pService.getAllProduct();

        for (Product p : listOfProduct) {
            listOfProductResponseDTO.add(convertProductToResponseDTO(p));
        }

//Implementing Main Sorting Logic here, it using custom comparator to do it.

        Comparator<productResponseDTO> comparator;

        switch (sortBy.toLowerCase()) {
            case "name":
                comparator = pcc.byProductName();
                break;
            case "pricelow":
                comparator = pcc.byProductPriceLow();
                break;
            case "pricehigh":
                comparator = pcc.byProductPriceHigh();
                break;
            default:
                comparator = pcc.byProductPriceLow(); // Default comparator by ID
        }

        listOfProductResponseDTO.sort(comparator);

        return listOfProductResponseDTO;


    }


    @GetMapping("/allproduct")
    public List<productResponseDTO> AllProduct() {

        List<productResponseDTO> listOfProductResponseDTO = new ArrayList<>();
        List<Product> listOfProduct = pService.getAllProduct();

        for (Product p : listOfProduct) {
            listOfProductResponseDTO.add(convertProductToResponseDTO(p));
        }
        return listOfProductResponseDTO;

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
}