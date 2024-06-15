package com.example.ecom.service;

import com.example.ecom.dto.fakestoreResponseDTO;
import com.example.ecom.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class fakeStoreServices implements productService {

    private RestTemplate restTemplate;

    public fakeStoreServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(Integer id) {

        ResponseEntity<fakestoreResponseDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, fakestoreResponseDTO.class);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null)  {
            return null;
        }
        fakestoreResponseDTO fakestoreResponse = response.getBody();

        return fakestoreResponse.toProduct();
    }

    @Override
    public List<Product> getAllProduct() {

        List<Product> productList = new ArrayList<>();

        fakestoreResponseDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products/", fakestoreResponseDTO[].class);

        for (fakestoreResponseDTO dto : response) {
            productList.add(dto.toProduct());
        }

        return productList;
    }


    @Override
    public List<Product> getFetchAndSorted() {

        List<Product> productList = new ArrayList<>();
        fakestoreResponseDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products/", fakestoreResponseDTO[].class);
        for (fakestoreResponseDTO dto : response) {
            productList.add(dto.toProduct());
        }

        return productList;
    }


    @Override
    public Product addProduct(String title, String description, String image, Double price) {

        fakestoreResponseDTO requestBody = new fakestoreResponseDTO();
        requestBody.setTitle(title);
        requestBody.setDescription(description);
        requestBody.setImage(image);
        requestBody.setPrice(String.valueOf(price));

        fakestoreResponseDTO response = restTemplate.postForObject("https://fakestoreapi.com/products", requestBody, fakestoreResponseDTO.class);

        return response.toProduct();
    }


    @Override
    public Product deleteProductById(Integer id) {

        String url = "https://fakestoreapi.com/products/{id}";
        HttpEntity<Void> requestEntity = new HttpEntity<>(null);

        ResponseEntity<fakestoreResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                fakestoreResponseDTO.class,
                id
        );

        fakestoreResponseDTO responseBody = response.getBody();
        return responseBody.toProduct();

    }


}
