package com.example.ecom.configration;

import com.example.ecom.dto.productResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Configuration
public class productControllerComparator {


    public Comparator<productResponseDTO> byProductPriceLow() {
        return Comparator.comparing(productResponseDTO::getProductPrice);
    }

    public Comparator<productResponseDTO> byProductName() {
        return Comparator.comparing(productResponseDTO::getProductName);
    }

    public Comparator<productResponseDTO> byProductPriceHigh() {
        return Comparator.comparing(productResponseDTO::getProductPrice).reversed();
    }


}
