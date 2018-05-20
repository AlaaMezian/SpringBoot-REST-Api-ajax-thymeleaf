package com.waffa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.waffa.controller", "com.waffa.service", "com.waffa.config","com.waffa.security","com.waffa.mail","com.waffa.mail.thymleaf"})
@EnableJpaRepositories("com.waffa.respository")
@EntityScan(basePackages= {"com.waffa.entity"})
public class WafaaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WafaaApplication.class, args);
	}
	
	
}
