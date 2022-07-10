package com.midas.ticket.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "cors.allowed")
public record CorsConfig(
	String api, String[] origins, String[] methods
) {
}
