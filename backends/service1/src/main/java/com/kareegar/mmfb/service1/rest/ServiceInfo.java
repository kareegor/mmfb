package com.kareegar.mmfb.service1.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Gateway configuration.
 */
@RestController
@RequestMapping("/api")
public class ServiceInfo {
    @Autowired
    Service2Proxy proxyService;
   
    @GetMapping("/info")
    public String info() {
        return "Hello :: Service1";
    }

    @GetMapping("/infoall")
    public ResponseEntity<List<String>> infoall() {
        String service2Info = proxyService.info();
        return ResponseEntity.ok(Arrays.asList(info(),service2Info));
    }
}
