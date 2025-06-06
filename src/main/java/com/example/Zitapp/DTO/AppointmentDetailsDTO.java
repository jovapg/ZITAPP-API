package com.example.Zitapp.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

// DTO Principal para la respuesta de una cita
public class AppointmentDetailsDTO {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado; // Puedes usar un String para el estado

    // DTOs anidados que representan información del cliente, negocio y servicio
    private ClientInfoDTO client;
    private BusinessInfoDTO business;
    private ServiceInfoDTO service;

    // --- Constructor vacío para serialización JSON ---
    public AppointmentDetailsDTO() {}

    // --- Constructor con todos los campos para fácil creación ---
    public AppointmentDetailsDTO(Long id, LocalDate fecha, LocalTime hora, String estado,
                                 ClientInfoDTO client, BusinessInfoDTO business, ServiceInfoDTO service) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.client = client;
        this.business = business;
        this.service = service;
    }

    // --- Getters y Setters para AppointmentDetailsDTO ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public ClientInfoDTO getClient() { return client; }
    public void setClient(ClientInfoDTO client) { this.client = client; }

    public BusinessInfoDTO getBusiness() { return business; }
    public void setBusiness(BusinessInfoDTO business) { this.business = business; }

    public ServiceInfoDTO getService() { return service; }
    public void setService(ServiceInfoDTO service) { this.service = service; }


    // =====================================================================
    // === DTOs ANIDADOS COMO CLASES PÚBLICAS ESTÁTICAS INTERNAS ===
    // ¡CAMBIO CLAVE AQUÍ: AÑADE 'public static' a cada clase anidada!
    // =====================================================================

    // DTO para la información básica del cliente
    public static class ClientInfoDTO { // ¡AÑADIDO 'public static'!
        private Long id;
        private String nombre;
        private String email;
        private String telefono;

        public ClientInfoDTO() {}
        public ClientInfoDTO(Long id, String nombre, String email, String telefono) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.telefono = telefono;
        }

        // Getters y Setters para ClientInfoDTO
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
    }

    // DTO para la información básica del negocio
    public static class BusinessInfoDTO { // ¡AÑADIDO 'public static'!
        private Long id;
        private String nombre;
        private String categoria;
        private String direccion;
        private String telefono;

        public BusinessInfoDTO() {}
        public BusinessInfoDTO(Long id, String nombre, String categoria, String direccion, String telefono) {
            this.id = id;
            this.nombre = nombre;
            this.categoria = categoria;
            this.direccion = direccion;
            this.telefono = telefono;
        }

        // Getters y Setters para BusinessInfoDTO
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getCategoria() { return categoria; }
        public void setCategoria(String categoria) { this.categoria = categoria; }
        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
    }

    // DTO para la información básica del servicio de la cita
    public static class ServiceInfoDTO { // ¡AÑADIDO 'public static'!
        private Long id;
        private String nombre;
        private String descripcion;
        private Double precio;
        private int duracion;

        public ServiceInfoDTO() {}
        public ServiceInfoDTO(Long id, String nombre, String descripcion, Double precio, int duracion) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
            this.duracion = duracion;
        }

        // Getters y Setters para ServiceInfoDTO
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public Double getPrecio() { return precio; }
        public void setPrecio(Double precio) { this.precio = precio; }
        public int getDuracion() { return duracion; }
        public void setDuracion(int duracion) { this.duracion = duracion; }
    }
}