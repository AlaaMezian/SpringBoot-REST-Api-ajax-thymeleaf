package com.appcom.waffa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.appcom.waffa.service.StorageProperties;

@SpringBootApplication(exclude = {  SecurityAutoConfiguration.class })
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.appcom.waffa.controller","com.appcom.waffa.notification" ,"com.appcom.waffa.service", "com.appcom.waffa.config","com.appcom.waffa.security","com.appcom.waffa.mail","com.appcom.waffa.mail.thymleaf"})
@EnableJpaRepositories("com.appcom.waffa.respository")
@EntityScan(basePackages= {"com.appcom.waffa.entity"})
@EnableConfigurationProperties(StorageProperties.class)
public class WafaaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WafaaApplication.class, args);
	}
	
	
}
