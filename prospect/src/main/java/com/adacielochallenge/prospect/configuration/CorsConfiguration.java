package com.adacielochallenge.prospect.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(CorsConfiguration.class);

    private final Dotenv dotenv;

    @Autowired
    public CorsConfiguration(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String allowedOriginsString = dotenv.get("CORS_ALLOWED_ORIGINS");
        String[] allowedOrigins = allowedOriginsString.split(",");

        for (String allowedOrigin : allowedOrigins) {
            LOG.info("allowed origin: " + allowedOrigin);
        }

        registry
                .addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
