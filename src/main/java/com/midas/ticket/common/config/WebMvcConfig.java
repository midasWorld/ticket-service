package com.midas.ticket.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties({CorsConfig.class})
public class WebMvcConfig implements WebMvcConfigurer {

	private final CorsConfig corsConfig;

	public WebMvcConfig(CorsConfig corsConfig) {
		this.corsConfig = corsConfig;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping(corsConfig.api())
			.allowedOrigins(corsConfig.origins())
			.allowedMethods(corsConfig.methods())
		;
	}
}
