package com.example.Zitapp.Servicios;

import com.example.Zitapp.Modelos.Notification;
import com.example.Zitapp.Modelos.Notification.TipoUsuario;
import com.example.Zitapp.Modelos.Notification.TipoNotification;
import com.example.Zitapp.Repositorios.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Crear y guardar una notificación
    public Notification crearNotificasion(Long senderId, TipoUsuario senderType,
                                          Long recipientId, TipoUsuario recipientType,
                                          String message, TipoNotification type,
                                          Long appointmentId) {
        Notification notification = new Notification();
        notification.setSenderId(senderId);
        notification.setSenderType(senderType);
        notification.setRecipientId(recipientId);
        notification.setRecipientType(recipientType);
        notification.setMessage(message);
        notification.setType(type);
        notification.setAppointmentId(appointmentId);
        notification.setRead(false);
        notification.setDeleted(false);
        notification.setCreatedAt(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

    // Obtener todas las notificaciones (leídas y no leídas, excepto eliminadas)
    public List<Notification> obtenerNotificacionesParaelususario(Long recipientId) {
        return notificationRepository.findByRecipientIdAndIsDeletedFalseOrderByCreatedAtDesc(recipientId);
    }

    // Obtener solo las no leídas
    public List<Notification> obtenerNotificacionesNoLeidasParaUsuario(Long recipientId) {
        return notificationRepository.findByRecipientIdAndIsReadFalseAndIsDeletedFalseOrderByCreatedAtDesc(recipientId);
    }

    // Marcar una notificación como leída
    public boolean marcarComoLeida(Long notificationId) {
        Optional<Notification> otp = notificationRepository.findById(notificationId);
        if (otp.isPresent()) {
            Notification notification = otp.get();
            notification.setRead(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }

    // "Eliminar" una notificación (marcarla como eliminada)
    public boolean eliminarNotificacion(Long notificationId) {
        Optional<Notification> opt = notificationRepository.findById(notificationId);
        if (opt.isPresent()) {
            Notification notification = opt.get();
            notification.setDeleted(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }
    // Obtener solo las leídas
    public List<Notification> obtenerNotificacionesLeidasParaUsuario(Long recipientId) {
        return notificationRepository.findByRecipientIdAndIsReadTrueAndIsDeletedFalseOrderByCreatedAtDesc(recipientId);
    }
}
