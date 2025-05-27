package com.example.Zitapp.Modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity

public class Users {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false,length = 50)
    private  String nombre;
    private  String telefono;
    private  String email;
    private  String contrasena;
    private  short  edad;

    @Column(name = "imagen_perfil")
    private  String imagenPerfil;

    public enum TipoUsuario {
        CLIENTE,
        NEGOCIO,
        ADMIN
    }

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    protected Users(){
    }

    public Users(String contrasena, String email, Long id, String nombre, String telefono, TipoUsuario tipo) {
        this.contrasena = contrasena;
        this.email = email;
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public TipoUsuario getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
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

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Appointments> appointments;
}




