package com.seu.universe.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue EmailQueue() {
        return new Queue(Constants.EMAIL_TOPIC);
    }

    @Bean
    public Queue MessageQueue() {
        return new Queue(Constants.MESSAGE_TOPIC);
    }
}
