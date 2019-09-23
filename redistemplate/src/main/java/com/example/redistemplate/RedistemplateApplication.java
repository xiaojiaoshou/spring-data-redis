package com.example.redistemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedistemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistemplateApplication.class, args);
    }

}
