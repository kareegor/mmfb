package com.kareegar.mmfb.service1.conf;

import com.kareegar.mmfb.service1.client.TokenRelayInterceptor;
import com.kareegar.mmfb.service1.util.TokenUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;

@Component
public class OAuth2InterceptedFeignConfiguration {

    @Bean(name = "oauth2RequestInterceptor")
    public RequestInterceptor getOAuth2RequestInterceptor(TokenUtil authorizationHeaderUtil) {
        return new TokenRelayInterceptor(authorizationHeaderUtil);
    }
}
