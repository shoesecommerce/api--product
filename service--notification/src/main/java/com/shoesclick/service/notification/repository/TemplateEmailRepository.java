package com.shoesclick.service.notification.repository;


import com.shoesclick.service.notification.entity.TemplateEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateEmailRepository extends JpaRepository<TemplateEmail, Long> {
}

