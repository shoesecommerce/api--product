package com.shoesclick.service.notification.service;

import com.shoesclick.service.notification.aspect.InjectLogger;
import com.shoesclick.service.notification.entity.Notification;
import com.shoesclick.service.notification.entity.OrderNotification;
import com.shoesclick.service.notification.repository.CustomerRepository;
import com.shoesclick.service.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final CustomerRepository customerRepository;

    private final NotificationRepository notificationRepository;

    private final TemplateEmailService templateEmailService;

    private final EmailService emailService;

    public NotificationService(CustomerRepository customerRepository, NotificationRepository notificationRepository, TemplateEmailService templateEmailService, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.notificationRepository = notificationRepository;
        this.templateEmailService = templateEmailService;
        this.emailService = emailService;
    }

    @InjectLogger
    public void process(OrderNotification order) {
            var customer = customerRepository.findById(order.getIdCustomer());
            var template = templateEmailService.findByTypeTemplate(order.getTypeTemplate());
            emailService.sendEmail(template.getBody(), customer.getEmail(), template.getSubject());
            var notification = new Notification();
            notificationRepository.save(notification);
    }
}

