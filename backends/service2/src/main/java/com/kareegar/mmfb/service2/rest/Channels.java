package com.kareegar.mmfb.service2.rest;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;

public interface Channels extends Processor{

    @Output("output1")
    MessageChannel output1();
}