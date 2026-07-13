package com.hibernate.ferreteria.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails { // <-- 1. IMPORTANTE: Implementar UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 60)
    private String rol;

    // --- MÉTODOS OBLIGATORIOS DE USERDETAILS PARA SPRING SECURITY ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convierte tu string 'rol' (ej: "USER") en un objeto que Spring entienda
        return List.of(new SimpleGrantedAuthority(this.rol));
    }

    @Override
    public String getUsername() {
        return this.usuario; // Le dice a Spring que tu campo 'usuario' es el username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Obligatorio que devuelva true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Obligatorio que devuelva true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Obligatorio que devuelva true
    }

    @Override
    public boolean isEnabled() {
        return true; // Obligatorio que devuelva true
    }

    // --- GETTERS Y SETTERS TRADICIONALES ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}