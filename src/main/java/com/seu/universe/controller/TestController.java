package com.seu.universe.controller;

import com.seu.universe.config.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping("/sendMq")
    @ResponseBody
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(Constants.EMAIL_TOPIC, context);
    }
}
