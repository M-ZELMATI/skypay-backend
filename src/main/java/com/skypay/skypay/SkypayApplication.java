package com.skypay.skypay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SkypayApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SkypayApplication.class, args);
    }
}
