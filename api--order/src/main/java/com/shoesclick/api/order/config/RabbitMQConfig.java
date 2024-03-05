package com.shoesclick.api.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    @Value("${rabbitmq.notification.queue.name}")
    private String notificationQueue;
    @Value("${rabbitmq.notification.exchange.name}")
    private String notificationExchange;
    @Value("${rabbitmq.notification.routing.key}")
    private String routingNotificationKey;

    @Value("${rabbitmq.payment.queue.name}")
    private String paymentQueue;

    @Value("${rabbitmq.payment.exchange.name}")
    private String paymentExchange;

    @Value("${rabbitmq.payment.routing.key}")
    private String routingPaymentKey;

    @Bean
    public Queue notificationQueue(){
        return new Queue(notificationQueue);
    }

    @Bean
    public TopicExchange notificationExchange(){
        return new TopicExchange(notificationExchange);
    }

    @Bean
    public Binding notificationBinding(){
        return BindingBuilder
                .bind(notificationQueue())
                .to(notificationExchange())
                .with(routingNotificationKey);
    }

    @Bean
    public Queue paymentQueue(){
        return new Queue(paymentQueue);
    }

    @Bean
    public TopicExchange paymentExchange(){
        return new TopicExchange(paymentExchange);
    }

    @Bean
    public Binding paymentBinding(){
        return BindingBuilder
                .bind(paymentQueue())
                .to(paymentExchange())
                .with(routingPaymentKey);
    }
}
