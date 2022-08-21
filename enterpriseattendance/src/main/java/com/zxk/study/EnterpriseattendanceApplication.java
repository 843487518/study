package com.zxk.study;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class EnterpriseattendanceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(EnterpriseattendanceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(Application.class);
    }

}
