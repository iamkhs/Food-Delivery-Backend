package com.iamkhs.fooddelivery.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyBeanManager {

    @Value("${cloudinary.api.key}")
    private String API_KEY;

    @Value("${cloudinary.api.secret}")
    private String API_SECRET;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Cloudinary getCloudinary(){
        Map<?, ?> config = new HashMap<>(
                Map.of("cloud_name", "dqkgbredv",
                        "api_key", API_KEY,
                        "api_secret", API_SECRET,
                        "secure", true)
        );
        return new Cloudinary(config);
    }
}
