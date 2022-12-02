package com.musicdatabase.service;

import com.musicdatabase.service.presentation.View;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServiceApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServiceApplication.class, args);
//        View view = context.getBean(View.class);
//        view.initialize();
    }
}
