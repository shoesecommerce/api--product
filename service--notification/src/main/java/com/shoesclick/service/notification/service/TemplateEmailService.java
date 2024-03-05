package com.shoesclick.service.notification.service;


import com.shoesclick.service.notification.entity.TemplateEmail;
import com.shoesclick.service.notification.enums.TypeTemplate;
import com.shoesclick.service.notification.repository.TemplateEmailRepository;
import org.springframework.stereotype.Service;

@Service
public class TemplateEmailService {

    private final TemplateEmailRepository templateEmailRepository;

    public TemplateEmailService(TemplateEmailRepository templateEmailRepository) {
        this.templateEmailRepository = templateEmailRepository;
    }

    public void save(TemplateEmail templateEmail) {
            templateEmailRepository.save(templateEmail);
    }

    public TemplateEmail findByTypeTemplate(TypeTemplate typeTemplate){
        return templateEmailRepository.findByTypeTemplate(typeTemplate);
    }
}
