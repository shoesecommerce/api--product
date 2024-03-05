package com.shoesclick.service.notification.entity;

import jakarta.persistence.*;

@Entity
@Table(name="template_email")
public class TemplateEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String body;

    public Long getId() {
        return id;
    }

    public TemplateEmail setId(Long id) {
        this.id = id;
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
