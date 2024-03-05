package com.shoesclick.service.notification.service;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(emailService, "mailFrom", "valor");
    }

    @Test
    void shouldSendEmailSuccess(){
        when(mailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        emailService.sendEmail("CONTENT","email@email.com","TITULO");
        verify(mailSender,times(1)).send(any(MimeMessage.class));
    }
}
