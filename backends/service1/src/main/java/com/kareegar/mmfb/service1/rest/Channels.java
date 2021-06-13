package com.kareegar.mmfb.service1.rest;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    @Output("output1")
    MessageChannel output1();
}