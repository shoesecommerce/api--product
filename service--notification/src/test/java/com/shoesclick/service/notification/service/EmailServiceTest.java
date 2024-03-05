package com.shoesclick.service.notification.service;

import com.shoesclick.service.notification.exception.SendEmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmailServiceTest {

    @Spy
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

    @Test
    void shouldThrowsSendEmailExceptionSendEmail(){
        when(mailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        doThrow(RuntimeException.class).doNothing().when(mailSender).send(any(MimeMessage.class));
        assertThrows(SendEmailException.class, ()-> emailService.sendEmail("CONTENT","email@email.com","TITULO"));
    }
}
