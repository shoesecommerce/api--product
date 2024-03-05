package com.shoesclick.service.notification.entity;

import com.shoesclick.service.notification.enums.TypeTemplate;
import jakarta.persistence.*;

@Entity
@Table(name="template_email")
public class TemplateEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeTemplate typeTemplate;

    private String subject;

    private String body;

    public Long getId() {
        return id;
    }

    public TemplateEmail setId(Long id) {
        this.id = id;
        return this;
    }

    public TypeTemplate getTypeTemplate() {
        return typeTemplate;
    }

    public TemplateEmail setTypeTemplate(TypeTemplate typeTemplate) {
        this.typeTemplate = typeTemplate;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public TemplateEmail setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getBody() {
        return body;
    }

    public TemplateEmail setBody(String body) {
        this.body = body;
        return this;
    }
}
