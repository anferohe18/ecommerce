package com.applaudo.andres.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private static final String SCOPE = "SCOPE_email";

    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests(authUser -> authUser
                        .antMatchers(HttpMethod.GET, "/actuator/health/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/**").hasAuthority(SCOPE)
                        .antMatchers(HttpMethod.POST, "/api/auth/**").hasAuthority(SCOPE)
                        .antMatchers(HttpMethod.GET, "/actuator/metrics/**").hasAuthority(SCOPE)
                        .antMatchers(HttpMethod.GET, "/actuator/loggers/**").hasAuthority(SCOPE)
                        .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

}