package com.shoesclick.service.notification.service;

import com.shoesclick.service.notification.entity.TemplateEmail;
import com.shoesclick.service.notification.enums.TypeTemplate;
import com.shoesclick.service.notification.repository.TemplateEmailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TemplateEmailServiceTest {

    @Mock
    private TemplateEmailRepository templateEmailRepository;

    @InjectMocks
    private TemplateEmailService templateEmailService;

    @Test
    void shouldReturnTemplateEmailFindByTypeTemplateSuccess(){
        when(templateEmailRepository.findByTypeTemplate(any(TypeTemplate.class))).thenReturn(new TemplateEmail()
                .setBody("BODY")
                .setSubject("SUBJECT")
                .setTypeTemplate(TypeTemplate.CREATE_ORDER));

        var template = templateEmailService.findByTypeTemplate(TypeTemplate.CREATE_ORDER);
        assertNotNull(template);
    }

    @Test
    void shouldReturnNullFindByTypeTemplate(){
        when(templateEmailRepository.findByTypeTemplate(any(TypeTemplate.class))).thenReturn(null);
        var template = templateEmailService.findByTypeTemplate(TypeTemplate.CREATE_ORDER);
        assertNull(template);
    }

    @Test
    void shouldSaveTemplateEmailSuccess(){
        templateEmailService.save(new TemplateEmail()
                .setBody("BODY")
                .setSubject("SUBJECT")
                .setTypeTemplate(TypeTemplate.CREATE_ORDER));
        verify(templateEmailRepository, times(1)).save(any(TemplateEmail.class));
    }


}
