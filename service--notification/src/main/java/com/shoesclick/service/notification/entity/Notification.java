package com.shoesclick.service.notification.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name="notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    public Long getId() {
        return id;
    }

    public Notification setId(Long id) {
        this.id = id;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Notification setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }
}


