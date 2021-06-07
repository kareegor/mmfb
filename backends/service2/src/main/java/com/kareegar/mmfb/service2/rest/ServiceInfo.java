package com.kareegar.mmfb.service2.rest;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/info")
    public String info() {
        return "Hello :: Service2";
}
}