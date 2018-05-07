package com.waffa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.waffa.controller", "com.waffa.service", "com.waffa.config"})
@EnableJpaRepositories("com.waffa.respository")
@EntityScan(basePackages= {"com.waffa.Entity"})
public class WafaaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WafaaApplication.class, args);
	}
}
