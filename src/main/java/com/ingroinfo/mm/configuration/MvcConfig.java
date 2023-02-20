package com.ingroinfo.mm.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		Path uploadDir = Paths.get("C:/Company");
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/Company/**").addResourceLocations("file:/" + uploadPath + "/");

		/*
		 * registry.addResourceHandler("/images/**").addResourceLocations(
		 * "/WEB-INF/images/") .setCacheControl(CacheControl.maxAge(2,
		 * TimeUnit.HOURS).cachePublic());
		 */
	}
}
