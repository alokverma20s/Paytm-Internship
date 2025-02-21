package com.scm.config;

import com.scm.entities.User;
import com.scm.services.UserService;
import com.scm.services.impl.SecurityCustomUserDetailService;
import com.scm.utils.JwtFilter;
import com.scm.utils.JwtUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityCustomUserDetailService userDetailService;
    private final JwtFilter jwtFilter;
    private final UserService userService;
    private final AppConfig appConfig;
    private final JwtUtil jwtUtil;

    public SecurityConfig(
        SecurityCustomUserDetailService userDetailService, 
        JwtFilter jwtFilter,
        JwtUtil jwtUtil,
        UserService userService, 
        AppConfig appConfig) {
        this.userDetailService = userDetailService;
        this.jwtFilter = jwtFilter;
        this.userService = userService;
        this.appConfig = appConfig;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(appConfig.passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/login", "/auth/register").permitAll(); // Public APIs
                    auth.requestMatchers("/user/**").authenticated(); // Protected Routes
                    auth.anyRequest().permitAll();
                })
                .oauth2Login(oauth2 -> oauth2
                        .successHandler((request, response, authentication) -> {
                            var principal = authentication.getPrincipal();
                            if (principal instanceof OAuth2User oAuth2User) {
                                // Store user in DB if first-time login
                                System.out.println(oAuth2User.getName());
                                User user = userService.processOAuthPostLogin(oAuth2User, "GITHUB");

                                // Generate JWT Token
                                String jwtToken = jwtUtil.generateToken(user.getEmail());

                                // Redirect user to frontend with token
                                response.sendRedirect("http://localhost:5173/oauth-success?token=" + jwtToken);
                            }
                        }))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}