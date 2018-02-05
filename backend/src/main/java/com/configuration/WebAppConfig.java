package com.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
// <mvc:annotation-driven></mvc:annotation-driven>
@ComponentScan(basePackages = "com")
// <context:component-scan base-package="com.niit"></context:component-scan>

public class WebAppConfig extends WebMvcConfigurerAdapter{

}
