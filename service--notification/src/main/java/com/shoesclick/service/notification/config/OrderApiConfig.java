package com.shoesclick.service.notification.config;

import com.shoesclick.service.notification.config.properties.MqProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MqProperties.class)
public class OrderApiConfig {
}
