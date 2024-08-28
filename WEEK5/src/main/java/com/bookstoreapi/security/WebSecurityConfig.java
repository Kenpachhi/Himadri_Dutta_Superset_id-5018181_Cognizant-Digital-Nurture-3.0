package com.bookstoreapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailService customUserDetailService;
    private final UnauthorizedHandler unauthorizedHandler;
    
	private static final String[] AUTH_WHITELIST = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**",
			"/swagger-ui/**", "/api-docs/swagger-config", "/api-docs" };

    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin(AbstractHttpConfigurer::disable)
            .exceptionHandling(h -> h.authenticationEntryPoint(unauthorizedHandler))
            .securityMatcher("/**")
            .authorizeHttpRequests(registry -> registry
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/auth/token").permitAll()
                    .requestMatchers(AUTH_WHITELIST).permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/books").hasRole("ADMIN")
                    .requestMatchers("/customers").hasRole("ADMIN")
                    .anyRequest().authenticated()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
        return builder.build();
    }
}
