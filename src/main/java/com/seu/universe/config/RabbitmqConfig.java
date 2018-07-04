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
    public Queue TestQUeue() {
        return new Queue(Constants.TEST_TOPIC);
    }

    @Bean
    public Queue PublicMessageQueue() {
        return new Queue(Constants.PUBLIC_MESSAGE_TOPIC);
    }

    @Bean
    public Queue DeleteMessageQueue() {
        return new Queue(Constants.DELETE_MESSAGE_TOPIC);
    }

    @Bean
    public Queue LikeMessageQueue() { return new Queue(Constants.LIKE_TOPIC); }
}
