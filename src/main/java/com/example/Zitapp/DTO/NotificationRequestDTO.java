// src/main/java/com/example/Zitapp/DTO/NotificationRequestDTO.java
package com.example.Zitapp.DTO;

// Importar los enums directamente desde la clase Notification
import com.example.Zitapp.Modelos.Notification.TipoUsuario;
import com.example.Zitapp.Modelos.Notification.TipoNotification;

public class NotificationRequestDTO {
    private Long senderId;
    private TipoUsuario senderType;
    private Long recipientId;
    private TipoUsuario recipientType;
    private String message;
    private TipoNotification type;
    private Long appointmentId;

    // Getters y Setters
    // ... (el resto de tus getters y setters existentes)
    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public TipoNotification getType() {
        return type;
    }

    public void setType(TipoNotification type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TipoUsuario getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(TipoUsuario recipientType) {
        this.recipientType = recipientType;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public TipoUsuario getSenderType() {
        return senderType;
    }

    public void setSenderType(TipoUsuario senderType) {
        this.senderType = senderType;
    }
}