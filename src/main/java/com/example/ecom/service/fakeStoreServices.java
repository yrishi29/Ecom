package com.example.ecom.service;

import com.example.ecom.dto.fakestoreResponseDTO;
import com.example.ecom.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class fakeStoreServices implements productService {

    private RestTemplate restTemplate;

    public fakeStoreServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Product getProductById (Integer id){

        ResponseEntity<fakestoreResponseDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, fakestoreResponseDTO.class);
        fakestoreResponseDTO fakestoreResponse = response.getBody();
        return fakestoreResponse.toProduct();

    }

    @Override
    public Product getAllProduct() {
        return null;
    }

    @Override
    public Product addProduct(String title, String description, String image, Double price) {

        fakestoreResponseDTO requestBody = new fakestoreResponseDTO();
        requestBody.setTitle(title);
        requestBody.setDescription(description);
        requestBody.setImage(image);
        requestBody.setPrice(String.valueOf(price));

        fakestoreResponseDTO response  =restTemplate.postForObject("https://fakestoreapi.com/products",requestBody, fakestoreResponseDTO.class);

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
