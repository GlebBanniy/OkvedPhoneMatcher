package com.example.okvedphonematcher.config;

import com.example.okvedphonematcher.config.properties.OkvedPhoneMatchProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = OkvedPhoneMatchProperties.class)
public class AppConfig {
}
