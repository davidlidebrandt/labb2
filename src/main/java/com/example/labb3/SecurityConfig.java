package com.example.labb3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.httpBasic(Customizer.withDefaults()).
            logout(AbstractHttpConfigurer::disable).
            csrf(AbstractHttpConfigurer::disable).
            cors(AbstractHttpConfigurer::disable).

            authorizeHttpRequests(authorize -> {
                authorize.requestMatchers(HttpMethod.GET, "/api/categories").permitAll().
                anyRequest().denyAll();
    }).build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(32);
    }

}
