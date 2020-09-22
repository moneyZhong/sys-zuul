package com.sys;

import com.sys.filter.AuthFilter;
import com.sys.filter.CrossFilter;
import com.sys.filter.ErrorFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableHystrixDashboard
@EnableZuulProxy
@SpringBootApplication
@MapperScan("com.sys.dao")
public class SysZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysZuulApplication.class, args);
    }
    @Bean
    public CrossFilter crossFilter(){
        return new CrossFilter();
    }

    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }

}
