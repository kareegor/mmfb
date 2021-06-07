package com.kareegar.mmfb.frontend1.gateway.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import java.util.Optional;

import com.kareegar.mmfb.frontend1.util.TokenUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

public class TokenRelayFilter extends ZuulFilter {

    private final TokenUtil headerUtil;
    public static final String AUTHORIZATION = "Authorization";

    private final Logger log = LoggerFactory.getLogger(TokenRelayFilter.class);

    public TokenRelayFilter(TokenUtil headerUtil) {
        this.headerUtil = headerUtil;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Optional<String> authorizationHeader = headerUtil.getAuthorizationHeader();
        authorizationHeader.ifPresent(s -> ctx.addZuulRequestHeader(TokenRelayFilter.AUTHORIZATION, s));
        return null;
    }
}