package com.sys.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 开启访问Hystri.stream入口
 * 配置完成后重启启动zuul，访问 http://localhost:8888/hystrix.stream 或者
 * http://localhost:8888/actuator/hystrix.stream
 */
@Configuration
public class HystrixConfiguration {

    @Bean(name = "hystrixRegistrationBean")
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new HystrixMetricsStreamServlet(), "/hystrix.stream");
        registration.setName("hystrixServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }

    @Bean(name = "hystrixForTurbineRegistrationBean")
    public ServletRegistrationBean servletTurbineRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new HystrixMetricsStreamServlet(), "/actuator/hystrix.stream");
        registration.setName("hystrixForTurbineServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }
}