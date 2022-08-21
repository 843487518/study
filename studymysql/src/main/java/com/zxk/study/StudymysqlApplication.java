package com.zxk.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//@SpringBootApplication
//public class StudymysqlApplication{
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(StudymysqlApplication.class, args);
//    }
//}
@SpringBootApplication
public class StudymysqlApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(StudymysqlApplication.class, args);
    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//        return builder.sources(StudymysqlApplication.class);
//    }

}
