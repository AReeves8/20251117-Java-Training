package com.skillsorm.springoauth2google.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
            .authorizeHttpRequests(authorizeHttpRequests -> {

                // all requests coming in require authentication
                authorizeHttpRequests.anyRequest().authenticated();
            })
            .csrf(csrf -> csrf.disable()) // disabling csrf ONLY for demo simplicity
            
            // when oauth is onvloved, you need to manually configure cors to allow react frontend to communicate
            .cors(cors -> {
                cors.configurationSource(request -> {
                    
                    // configuring how we want to handle cors
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5173"));               // what origins are allowed
                    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));        // what http methods are allowed
                    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));       // what headers are allowed
                    corsConfig.setAllowCredentials(true);                                               // allow cookies to be sent to backend
                    corsConfig.setMaxAge(3600L);                                                        // how long to cache the cors preflight request (OPTIONS)

                    // setting which endpoints to apply the above cors configurations to
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", corsConfig);

                    return corsConfig;
                });
            });

        
        // Telling spring security to use our registered OAuth2 client
        http.oauth2Login();

        return http.build();
    }
}
