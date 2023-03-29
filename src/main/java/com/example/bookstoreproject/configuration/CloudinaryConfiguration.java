package com.example.bookstoreproject.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfiguration {
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "daci4gzii");
        config.put("api_key", "282111846996446");
        config.put("api_secret", "YyebHAhoXFfP9ll26a-R4TegE4w");
        return new Cloudinary(config);
    }
}
