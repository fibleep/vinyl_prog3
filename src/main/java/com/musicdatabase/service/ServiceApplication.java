package com.musicdatabase.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class ServiceApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServiceApplication.class, args);
    }
}
