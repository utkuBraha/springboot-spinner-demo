package com.example.spinnerdemo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;


    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
            UserDetails user = User.withUsername("user")
                    .password(passwordEncoder.encode("user123"))
                    .roles("USER")
                    .build();

            UserDetails admin = User.withUsername("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .roles("ADMIN", "USER")
                    .build();


            return new InMemoryUserDetailsManager(user, admin);
        }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .cors(withDefaults())
                    .csrf().disable() // disable csrf
                    .authorizeRequests(authorize -> authorize
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/user/**","/public/**").hasRole("USER")
                            .anyRequest().permitAll()
                    )
                    .formLogin(withDefaults())
                    .httpBasic()
                    .and()
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/afterLogout")
                            .invalidateHttpSession(true)
                            .deleteCookies("exit"));
            return http.build();
        }

    }


