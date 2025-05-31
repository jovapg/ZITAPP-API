package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Notification;
import com.example.Zitapp.Modelos.NotificationRequestDTO;
import com.example.Zitapp.Servicios.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

        @Autowired
        private NotificationService notificationService;

        @PostMapping("/custom")
        public ResponseEntity<Notification> crearNotificacionPersonalizada(@RequestBody NotificationRequestDTO dto) {
                Notification creada = notificationService.crearNotificasion(
                        dto.getSenderId(),
                        dto.getSenderType(),
                        dto.getRecipientId(),
                        dto.getRecipientType(),
                        dto.getMessage(),
                        dto.getType(),
                        dto.getAppointmentId()
                );
                return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        }

        // Obtener todas las notificaciones (leídas y no leídas)
        @GetMapping("/{userId}")
        public List<Notification> getNotificaciones(@PathVariable Long userId) {
                return notificationService.obtenerNotificacionesParaelususario(userId);
        }

        // Obtener solo las no leídas
        @GetMapping("/{userId}/unread")
        public List<Notification> getNoleido(@PathVariable Long userId) {
                return notificationService.obtenerNotificacionesNoLeidasParaUsuario(userId);
        }

        // Marcar como leída
        @PutMapping("/{notificationId}/read")
        public String marcarcomoleida(@PathVariable Long notificationId) {
                boolean ok = notificationService.marcarComoLeida(notificationId);
                return ok ? "Notificación marcada como leída" : "No se encontró la notificación";
        }

        // Eliminar (lógicamente)git
        @DeleteMapping("/{notificationId}")
        public String eliminarNotificacion(@PathVariable Long notificationId) {
                boolean ok = notificationService.eliminarNotificacion(notificationId);
                return ok ? "Notificación eliminada" : "No se encontró la notificación";
        }

        // Obtener solo las leídas
        @GetMapping("/{userId}/read")
        public List<Notification> getLeidas(@PathVariable Long userId) {
                return notificationService.obtenerNotificacionesLeidasParaUsuario(userId);
        }
}
