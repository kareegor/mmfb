package com.kareegar.mmfb.frontend1.conf;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    public SecurityConfiguration(CorsFilter corsFilter) {
        this.corsFilter = corsFilter;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/**").authenticated()           
        .and()
            .oauth2Login();
        //.and()
          //  .oauth2ResourceServer()
            //    .jwt();
             //   .jwtAuthenticationConverter(authenticationConverter());
            //    .and()
            // .and()
            //     .oauth2Client();
        // @formatter:on
    }
}