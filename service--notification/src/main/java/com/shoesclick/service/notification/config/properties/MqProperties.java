package com.shoesclick.service.notification.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public class MqProperties {

    private String exchange;

    private MQNotificationProperties notification;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public MQNotificationProperties getNotification() {
        return notification;
    }

    public void setNotification(MQNotificationProperties notification) {
        this.notification = notification;
    }
}
