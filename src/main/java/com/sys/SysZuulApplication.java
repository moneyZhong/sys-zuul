package com.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SysZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysZuulApplication.class, args);
    }

}
