package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyam.Ecommerce.Model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
