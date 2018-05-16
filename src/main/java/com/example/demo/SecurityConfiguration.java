package com.example.demo;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Filip Hrisafov
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Configuration
    @Order(15)
    public static class DefaultSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/**").permitAll();
        }
    }

    @Configuration
    @Order(5)
    @ConditionalOnProperty(name = "demo.matcher", havingValue = "base")
    public static class ActuatorBaseSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/actuator/**")
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll();
        }
    }

    @Configuration
    @Order(5)
    @ConditionalOnProperty(name = "demo.matcher", havingValue = "to-any")
    public static class ActuatorAnyEndpointSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll();
        }
    }

}
