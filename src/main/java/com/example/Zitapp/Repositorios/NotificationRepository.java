package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Buscar todas las notificaciones (leídas o no), no eliminadas
    List<Notification> findByRecipientIdAndIsDeletedFalseOrderByCreatedAtDesc(Long recipientId);

    // Buscar solo las leídas y no eliminadas
    List<Notification> findByRecipientIdAndIsReadTrueAndIsDeletedFalseOrderByCreatedAtDesc(Long recipientId);

    // Buscar solo las no leídas y no eliminadas
    List<Notification> findByRecipientIdAndIsReadFalseAndIsDeletedFalseOrderByCreatedAtDesc(Long recipientId);
}
