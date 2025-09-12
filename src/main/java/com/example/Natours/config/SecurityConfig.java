package com.example.Natours.config;

import com.example.Natours.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for API
                .httpBasic(httpBasic -> httpBasic.disable()) // ✅ No basic auth
                .formLogin(form -> form.disable()) // ✅ No login form
                .logout(logout -> logout.disable()) // ✅ No logout (JWT is stateless)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ✅ Stateless mode
                )
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() // ✅ allow login
                                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()  // ✅ allow register
                                .requestMatchers("/api/**").authenticated() // ✅ protect APIs
                                .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Arrays.asList(authProvider()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
