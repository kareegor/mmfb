package com.kareegar.mmfb.service1.conf;

import javax.servlet.Filter;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // public CorsFilter corsFilter() {
    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // CorsConfiguration config = new CorsConfiguration();
    // // allowed-origins: '*'
    // // allowed-methods: '*'
    // // allowed-headers: '*'
    // // exposed-headers: 'Authorization,Link,X-Total-Count'
    // // allow-credentials: true
    // // max-age: 1800
    // config.setAllowCredentials(true);
    // config.setAllowedHeaders(Arrays.asList("*"));
    // config.setAllowedOrigins(Arrays.asList("*"));
    // config.setExposedHeaders(Arrays.asList("Authorization","Link","X-Total-Count"));

    // source.registerCorsConfiguration("/api/**", config);
    // source.registerCorsConfiguration("/management/**", config);
    // source.registerCorsConfiguration("/v2/api-docs", config);
    // source.registerCorsConfiguration("/*/api/**", config);
    // source.registerCorsConfiguration("/services/*/api/**", config);
    // source.registerCorsConfiguration("/*/management/**", config);

    // return new CorsFilter(source);
    // }
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http.requestMatcher(EndpointRequest.toAnyEndpoint())
    //             .authorizeRequests((requests) -> requests.anyRequest().permitAll());
    //     return http.build();
    // }

    // private final Filter securityFilterChain;

    // public SecurityConfiguration(Filter securityFilterChain) {
    //     this.securityFilterChain = securityFilterChain;
    // }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/management/**").permitAll().antMatchers("/api/**").permitAll().and().authorizeRequests()
                .antMatchers("/**").authenticated().and().csrf().disable();
    }
}