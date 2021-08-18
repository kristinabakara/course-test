package com.cakap.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    //to handling cache in Rest API
    @Bean
    public Filter filter(){
        ShallowEtagHeaderFilter filter = new ShallowEtagHeaderFilter();
        return filter;
    }

}
