package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

/**
 * Entidad que representa un servicio ofrecido por un negocio.
 */
@Entity
@Table(name = "services")
public class BusinnesService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del servicio", example = "1")
    private Long id;

    @Schema(description = "Nombre del servicio", example = "Corte de cabello")
    private String nombre;

    @Schema(description = "Descripción del servicio", example = "Corte con máquina y tijeras")
    private String descripcion;

    @Schema(description = "Precio del servicio", example = "15000")
    private Double precio;

    @Schema(description = "Duración del servicio en minutos", example = "30")
    private int duracion; // <- ✅ Este es el campo que faltaba

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    @JsonBackReference("business-services")
    @Schema(description = "Negocio al que pertenece este servicio")
    private Business business;

    // Constructor vacío para JPA
    public BusinnesService() {}

    // Constructor con todos los campos (excepto ID)
    public BusinnesService(String nombre, String descripcion, Double precio, int duracion, Business business) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion = duracion;
        this.business = business;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
