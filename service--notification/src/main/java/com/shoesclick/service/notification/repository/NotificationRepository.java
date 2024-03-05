package com.shoesclick.service.notification.repository;


import com.shoesclick.service.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

