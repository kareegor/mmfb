package com.kareegar.mmfb.service1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
@EnableBinding(Processor.class)
public class Publisher {
   @Autowired
   private StreamBridge streamBridge;  

   @GetMapping("/pub")
   public String publish(@RequestParam String s) {
       streamBridge.send("topic1", s);
       return s;
   }
  
}