package com.example.cosmeticapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CosmeticApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosmeticApiApplication.class, args);
    }

}
