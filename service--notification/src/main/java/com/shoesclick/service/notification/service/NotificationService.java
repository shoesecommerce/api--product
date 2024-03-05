package com.shoesclick.service.notification.service;

import com.shoesclick.service.notification.entity.Log;
import com.shoesclick.service.notification.entity.Notification;
import com.shoesclick.service.notification.entity.OrderNotification;
import com.shoesclick.service.notification.enums.TypeLog;
import com.shoesclick.service.notification.repository.CustomerRepository;
import com.shoesclick.service.notification.repository.LogRepository;
import com.shoesclick.service.notification.repository.NotificationRepository;
import com.shoesclick.service.notification.repository.TemplateEmailRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final CustomerRepository customerRepository;

    private final NotificationRepository notificationRepository;

    private final TemplateEmailRepository templateEmailRepository;

    private final LogRepository logRepository;

    private final EmailService emailService;

    public NotificationService(CustomerRepository customerRepository, NotificationRepository notificationRepository, TemplateEmailRepository templateEmailRepository, LogRepository logRepository, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.notificationRepository = notificationRepository;
        this.templateEmailRepository = templateEmailRepository;
        this.logRepository = logRepository;
        this.emailService = emailService;
    }


    public void process(OrderNotification order) {
        Log log = null;
        try {
            var customer = customerRepository.findById(order.getIdCustomer());
            var template = templateEmailRepository.findByTypeTemplate(order.getTypeTemplate());
            emailService.sendEmail(template.getBody(), customer.getEmail(), template.getSubject());
            var notification = new Notification();
            notificationRepository.save(notification);
            log = setLog("PROCESSADO COM SUCESSO A NOTIFICAÇÃO " + order.getIdOrder(), "EXCECUTADO COM SUCESSO", TypeLog.SUCCESS);
        } catch (Exception e) {
            log = setLog("ERRO AO PROCESSAR A NOTIFICAÇÃO " + order.getIdOrder(), e.getMessage(), TypeLog.ERROR);
        } finally {
            logRepository.save(log);
        }
    }

    private Log setLog(String message, String details, TypeLog success) {
        return new Log().setClassName(this.getClass().getName())
                .setError(message)
                .setDetails(details)
                .setTypeLog(success);
    }
}

