package com.kareegar.mmfb.service1.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="service2")
public interface Service2Proxy {
    @RequestMapping("/service2/api/info")
    public String info();
}