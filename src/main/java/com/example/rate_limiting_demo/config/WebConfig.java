package com.example.rate_limiting_demo.config;


import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(@NonNull ApiVersionConfigurer configurer) {
        configurer.useRequestHeader("X-Api-Version");
    }
}
