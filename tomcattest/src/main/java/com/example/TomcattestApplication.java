package com.example;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TomcattestApplication {

    public static void main(String[] args) {

        SpringApplication.run(TomcattestApplication.class, args);
    }
//public class TomcattestApplication extends SpringBootServletInitializer {
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(TomcattestApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//        return builder.sources(TomcattestApplication.class);
//    }
}
