package com.appcom.waffa.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlerConfiguration implements WebMvcConfigurer {
	
	//
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/home/Images/**")
				.addResourceLocations(new File("/home/Images").toURI().toString());

	}
}
