package com.shoesclick.service.notification.repository;


import com.shoesclick.service.notification.entity.TemplateEmail;
import com.shoesclick.service.notification.enums.TypeTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateEmailRepository extends JpaRepository<TemplateEmail, Long> {

    TemplateEmail findByTypeTemplate(TypeTemplate typeTemplate);
}

