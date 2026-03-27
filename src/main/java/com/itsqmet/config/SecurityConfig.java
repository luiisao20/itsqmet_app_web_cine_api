package com.itsqmet.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.itsqmet.jwt.JwtTokenValidator;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults())
        .authorizeHttpRequests(auth -> auth
            // Paths solo para administradores
            .requestMatchers("/users/update/**", "/users/delete/**", "/users", "/users/refresh-view")
            .hasRole("ADMIN")
            // Paths solo para moderadores
            .requestMatchers("/categories/delete/**", "/categories/save", "/categories/update/**",
                "/contact/delete/**", "/contact", "/memberships", "/memberships/*", "/memberships/delete/**",
                "/movies/category-view", "/movies/delete/**", "/movies/refresh-financial", "/movies//refresh-category",
                "/movies/revenew-view", "/movies/save", "/movies/saveAll", "/movies/update/**",
                "/reviews/delete/**", "/schedules/delete/**", "/schedules/filters",
                "/schedules/save", "/schedules/saveAll", "/schedules/time-available", "/schedules/update/**",
                "/stablishments/delete/**", "/stablishments/save", "/stablishments/saveAll", "/stablishments/update/**",
                "/tickets", "/tickets/schedule/**", "/users/membership-view")
            .hasRole("MODERATOR")
            // Paths para users autenticados
            .requestMatchers("/auth/validate", "/auth/change-password", "/memberships/user/**",
                "/movies/**", "/reviews", "/reviews/save", "/reviews/delete/**", "/reviews/movie/**",
                "/schedules", "/schedules/**", "/schedules/movie/**", "/schedules/stablishment/*/movie/*",
                "/tickets/user/**", "/tickets/save", "/users/**", "/users/update/**")
            .authenticated()
            // Paths publicos
            .requestMatchers("/auth/login", "/auth/register",
                "/categories", "/categories/**", "/stablishments",
                "/contact/save", "/movies", "/movies/category/**", "/movies/stablishment/**")
            .permitAll())
        .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:4200"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

}
