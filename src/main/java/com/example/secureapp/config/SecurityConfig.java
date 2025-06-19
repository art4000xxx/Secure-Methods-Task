package com.example.secureapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/api/secure/read", true) // Перенаправление на рабочий эндпоинт
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails reader = User.withDefaultPasswordEncoder()
                .username("reader")
                .password("password")
                .roles("READ")
                .build();

        UserDetails writer = User.withDefaultPasswordEncoder()
                .username("writer")
                .password("password")
                .roles("WRITE")
                .build();

        UserDetails deleter = User.withDefaultPasswordEncoder()
                .username("deleter")
                .password("password")
                .roles("DELETE")
                .build();

        UserDetails multirole = User.withDefaultPasswordEncoder()
                .username("multirole")
                .password("password")
                .roles("WRITE", "DELETE")
                .build();

        manager.createUser(reader);
        manager.createUser(writer);
        manager.createUser(deleter);
        manager.createUser(multirole);

        return manager;
    }
}