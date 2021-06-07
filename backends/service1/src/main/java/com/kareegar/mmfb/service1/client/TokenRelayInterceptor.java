package com.kareegar.mmfb.service1.client;

import java.util.Optional;

import com.kareegar.mmfb.service1.util.TokenUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class TokenRelayInterceptor  implements RequestInterceptor {

    public static final String AUTHORIZATION = "Authorization";

    private final Logger log = LoggerFactory.getLogger(TokenRelayInterceptor.class);

    private final TokenUtil tokenUtil;

    public TokenRelayInterceptor(TokenUtil tokenUtil) {
        super();
        this.tokenUtil = tokenUtil;
    }

    @Override
    public void apply(RequestTemplate template) {
        Optional<String> authorizationHeader = tokenUtil.getAuthorizationHeader();
        authorizationHeader.ifPresent(s -> template.header(AUTHORIZATION, s));
    }
    

    
}