package com.adacielochallenge.prospect.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotEnvConfiguration {

    @Bean
    public Dotenv dotenv() {
        return Dotenv
                .configure()
                .ignoreIfMalformed()
                .load();
    }
}
