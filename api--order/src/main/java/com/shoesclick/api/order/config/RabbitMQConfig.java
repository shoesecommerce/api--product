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
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.notification.queue.name}")
    private String notificationQueue;

    @Value("${rabbitmq.notification.routing.key}")
    private String routingNotificationKey;

    @Value("${rabbitmq.payment.queue.name}")
    private String paymentQueue;

    @Value("${rabbitmq.payment.routing.key}")
    private String routingPaymentKey;

    @Bean
    public Queue notificationQueue(){
        return new Queue(notificationQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding notificationBinding(){
        return BindingBuilder
                .bind(notificationQueue())
                .to(exchange())
                .with(routingNotificationKey);
    }

    @Bean
    public Queue paymentQueue(){
        return new Queue(paymentQueue);
    }

    @Bean
    public Binding paymentBinding(){
        return BindingBuilder
                .bind(paymentQueue())
                .to(exchange())
                .with(routingPaymentKey);
    }
}
