package com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
// <mvc:annotation-driven></mvc:annotation-driven>
@ComponentScan(basePackages = "com")
// <context:component-scan base-package="com.niit"></context:component-scan>

public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver(){
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		return multipartResolver;
		
	}
	}
