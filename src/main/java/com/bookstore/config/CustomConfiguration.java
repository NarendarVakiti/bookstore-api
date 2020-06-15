package com.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.sleuth.sampler.SamplerProperties;

@Configuration
public class CustomConfiguration {

    @Bean
    public void defaultSampler(){

    }
}
