package com.example.labb3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                    authorize.requestMatchers(HttpMethod.GET, "/api/categories/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/places").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/places/*").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/categories").hasRole("ADMIN")
                            .anyRequest().denyAll();
                }).build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails newUser = User.builder().username("Joe").password(passwordEncoder().encode("password")).roles("USER").build();
        UserDetails newAdmin = User.builder().username("John").password(passwordEncoder.encode("password")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(newUser, newAdmin);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
