package com.shoesclick.service.notification.service;

import com.shoesclick.service.notification.entity.*;
import com.shoesclick.service.notification.enums.TypeTemplate;
import com.shoesclick.service.notification.exception.SendEmailException;
import com.shoesclick.service.notification.repository.CustomerRepository;
import com.shoesclick.service.notification.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.shoesclick.service.notification.service.mock.OrderNotificationMock.getOrderNotification;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class NotificationServiceTest extends AbstractServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private TemplateEmailService templateEmailService;


    @Mock
    private EmailService emailService;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setup() {
        notificationService = setupServiceTest(notificationService);
    }

    @Test
    void shouldProcessNotificationSuccess() {
        when(customerRepository.findById(anyLong())).thenReturn(
                new Customer()
                        .setId(1L)
                        .setEmail("email@email.com"));

        when(templateEmailService.findByTypeTemplate(any(TypeTemplate.class))).thenReturn(new TemplateEmail()
                .setBody("BODY")
                .setSubject("SUBJECT")
                .setTypeTemplate(TypeTemplate.CREATE_ORDER));

        notificationService.process(getOrderNotification());
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void shouldThrowExceptionCustomerRespositoryProcessNotification() {
        when(customerRepository.findById(anyLong())).thenThrow(new RuntimeException("ERROR"));
        notificationService.process(getOrderNotification());
        verify(emailService, times(0)).sendEmail(anyString(), anyString(), anyString());
        verify(notificationRepository, times(0)).save(any(Notification.class));
    }

    @Test
    void shouldThrowExceptionEmailServiceProcessNotification() {
        when(customerRepository.findById(anyLong())).thenReturn(
                new Customer()
                        .setId(1L)
                        .setEmail("email@email.com"));

        when(templateEmailService.findByTypeTemplate(any(TypeTemplate.class))).thenReturn(new TemplateEmail()
                .setBody("BODY")
                .setSubject("SUBJECT")
                .setTypeTemplate(TypeTemplate.CREATE_ORDER));

        doThrow(SendEmailException.class).doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());


        notificationService.process(getOrderNotification());
        verify(notificationRepository, times(0)).save(any(Notification.class));
    }
}
