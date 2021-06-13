package com.kareegar.mmfb.service2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
@Component
@EnableBinding(Channels.class)
public class Consumer {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String uppercase(String s) {
       return s.toUpperCase();
    }
    @StreamListener(Processor.OUTPUT)
    @SendTo("output1")
    public String greeting(String s) {
      return "Hello "+s;
    }
    @StreamListener("output1")
    public void handle(String s) {
       System.out.println("SERVICE2 Received: " + s);
    }
  
}