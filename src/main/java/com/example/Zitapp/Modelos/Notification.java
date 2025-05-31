package  com.example.Zitapp.Modelos;

import  jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public  class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  Long recipientId;

    @Enumerated(EnumType.STRING)
    private TipoUsuario recipientType;


    private Long senderId;

    @Enumerated(EnumType.STRING)
    private TipoUsuario senderType;

    private String message;

    @Enumerated(EnumType.STRING)
    private  TipoNotification type;

    private  Long appointmentId;
    @Column(nullable = false)
    private  Boolean isRead = false;
    @Column(nullable = false)
    private  Boolean isDeleted = false;

    private  LocalDateTime createdAt = LocalDateTime.now();

    // get y set

    public  enum  TipoUsuario{
        CLIENTE,
        NEGOCIO,
        ADMIN
    }
     public enum TipoNotification{
         CITA_CREADA,
         CITA_CANCELADA,
         CITA_CONFIRMADA,
         CITA_FINALIZADA
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public TipoUsuario getSenderType() {
        return senderType;
    }

    public void setSenderType(TipoUsuario senderType) {
        this.senderType = senderType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}