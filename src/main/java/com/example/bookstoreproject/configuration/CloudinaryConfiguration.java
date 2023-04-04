package com.example.bookstoreproject.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cloudinary")

public class CloudinaryConfiguration {

    private String name;

    private String secret;

    private String key;

    @Bean
    public Cloudinary cloudinary() {
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", name);
        config.put("api_key", key);
        config.put("api_secret", secret);
        return new Cloudinary(config);
    }
}
