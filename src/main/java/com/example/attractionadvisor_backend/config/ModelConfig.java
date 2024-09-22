package com.example.attractionadvisor_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ModelConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
