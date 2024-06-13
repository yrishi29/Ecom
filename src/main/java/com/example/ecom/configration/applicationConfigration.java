package com.example.ecom.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Configuration
public class applicationConfigration {


   @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }


}
