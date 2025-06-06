// src/main/java/com/example/Zitapp/Modelos/Users.java
package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Importa JsonManagedReference
import jakarta.persistence.*; // Importa todas las anotaciones de JPA
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa a un usuario en el sistema.")
@Entity
@Table(name = "users") // Asegúrate de que el nombre de la tabla sea correcto
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del usuario", example = "1")
    private Long id;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String nombre;

    @Column(nullable = false, length = 15) // Añadido nullable y length según buenas prácticas
    @Schema(description = "Teléfono del usuario", example = "3123456789")
    private String telefono;

    @Column(nullable = false, unique = true, length = 100) // Añadido unique y length
    @Schema(description = "Correo electrónico del usuario", example = "juan@example.com")
    private String email;

    @Column(nullable = false, length = 255) // Añadido nullable y length. Considera encriptar contraseñas.
    @Schema(description = "Contraseña del usuario (debería estar encriptada)", example = "******")
    private String contrasena;

    @Column(nullable = false) // Asegura que la edad no sea nula
    @Schema(description = "Edad del usuario", example = "25")
    private short edad;

    @Column(name = "imagen_perfil", length = 255) // Añadido length
    @Schema(description = "URL de la imagen de perfil del usuario", example = "http://example.com/profile.jpg")
    private String imagenPerfil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20) // Asegura que el tipo no sea nulo y tenga una longitud
    @Schema(description = "Tipo de usuario: CLIENTE, NEGOCIO o ADMIN", example = "CLIENTE")
    private TipoUsuario tipo;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Agregado cascade si quieres que las citas se borren con el usuario
    @JsonManagedReference("user-appointments")
    private List<Appointments> appointments;

    // --- ¡NUEVA RELACIÓN: Un usuario de tipo NEGOCIO tiene UN negocio! ---
    // Esta es la relación inversa. Business es el propietario del Foreign Key (id_usuario).
    // JsonBackReference es importante para evitar bucles infinitos en serialización JSON
    // si intentas serializar el Business a través del Users, y viceversa.
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Usamos JsonBackReference aquí porque Business es el lado que tiene el JoinColumn (id_usuario)
    // y Users es el lado "mappedBy"
    @JsonBackReference("user-business")
    private Business business;


    public enum TipoUsuario {
        CLIENTE,
        NEGOCIO,
        ADMIN
    }

    // Constructor vacío (requerido por JPA)
    public Users() {
    }

    // Constructor personalizado (ajustado para incluir todos los campos)
    public Users(String nombre, String telefono, String email, String contrasena, short edad, String imagenPerfil, TipoUsuario tipo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
        this.edad = edad;
        this.imagenPerfil = imagenPerfil;
        this.tipo = tipo;
    }

    // --- Getters y Setters ---
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }

    // --- Getter y Setter para la nueva relación con Business ---
    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipo=" + tipo +
                ", edad=" + edad +
                ", imagenPerfil='" + imagenPerfil + '\'' +
                (business != null ? ", businessId=" + business.getId() : ", businessId=null") + // Para depuración
                '}';
    }
}