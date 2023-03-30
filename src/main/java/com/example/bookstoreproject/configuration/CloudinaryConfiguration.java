package com.example.bookstoreproject.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:application-dev.properties"})
public class CloudinaryConfiguration {
    @Value("${cloudinary.name}")
    private String cloudinaryName;
    @Value("${cloudinary.secret}")
    private String cloudinarySecret;
    @Value("${cloudinary.key}")
    private String cloudinaryKey;

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudinaryName);
        config.put("api_key", cloudinaryKey);
        config.put("api_secret", cloudinarySecret);
        return new Cloudinary(config);
    }
}
