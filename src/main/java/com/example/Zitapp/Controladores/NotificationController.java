package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Notification;
import com.example.Zitapp.DTO.NotificationRequestDTO;
import com.example.Zitapp.Servicios.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

        @Operation(summary = "Crear notificación personalizada")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Notificación creada exitosamente",
                        content = @Content(schema = @Schema(implementation = Notification.class)))
        })
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

        @Operation(summary = "Obtener todas las notificaciones de un usuario")
        @ApiResponse(responseCode = "200", description = "Lista de notificaciones",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Notification.class))))
        @GetMapping("/{userId}")
        public List<Notification> getNotificaciones(@PathVariable Long userId) {
                return notificationService.obtenerNotificacionesParaelususario(userId);
        }

        @Operation(summary = "Obtener solo las notificaciones no leídas")
        @ApiResponse(responseCode = "200", description = "Lista de notificaciones no leídas",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Notification.class))))
        @GetMapping("/{userId}/unread")
        public List<Notification> getNoleido(@PathVariable Long userId) {
                return notificationService.obtenerNotificacionesNoLeidasParaUsuario(userId);
        }

        @Operation(summary = "Marcar una notificación como leída")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Notificación marcada como leída"),
                @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
        })
        @PutMapping("/{notificationId}/read")
        public String marcarcomoleida(@PathVariable Long notificationId) {
                boolean ok = notificationService.marcarComoLeida(notificationId);
                return ok ? "Notificación marcada como leída" : "No se encontró la notificación";
        }

        @Operation(summary = "Eliminar (lógicamente) una notificación")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Notificación eliminada"),
                @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
        })
        @DeleteMapping("/{notificationId}")
        public String eliminarNotificacion(@PathVariable Long notificationId) {
                boolean ok = notificationService.eliminarNotificacion(notificationId);
                return ok ? "Notificación eliminada" : "No se encontró la notificación";
        }

        @Operation(summary = "Obtener solo las notificaciones leídas")
        @ApiResponse(responseCode = "200", description = "Lista de notificaciones leídas",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Notification.class))))
        @GetMapping("/{userId}/read")
        public List<Notification> getLeidas(@PathVariable Long userId) {
                return notificationService.obtenerNotificacionesLeidasParaUsuario(userId);
        }
}
