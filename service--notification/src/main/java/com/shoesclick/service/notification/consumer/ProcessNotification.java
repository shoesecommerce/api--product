package com.shoesclick.service.notification.consumer;

import com.shoesclick.service.notification.repository.CustomerRepository;
import com.shoesclick.service.notification.repository.NotificationRepository;
import com.shoesclick.service.notification.service.CustomerService;
import com.shoesclick.service.notification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProcessNotification {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    private final EmailService emailService;

    private final CustomerService customerService;

    private final NotificationRepository notificationRepository;

    public ProcessNotification(EmailService emailService, CustomerRepository customerRepository, CustomerService customerService, NotificationRepository notificationRepository) {
        this.emailService = emailService;
        this.customerService = customerService;
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = "order_notification")
    public void handleMessage(String message){

    }
}
