package com.example.okvedphonematcher.config.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "okved-phone-mather")
public class OkvedPhoneMatchProperties {

    @NestedConfigurationProperty
    private OkvedSourceProperty okvedSource;

    @Data
    public static class OkvedSourceProperty {

        @NotBlank
        private String url;

        @NotBlank
        private String fileName;
    }
}
