package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Usuario implements UserDetails {

    @Id @GeneratedValue
    private long id;


    private String nombre;
    private String apellidos;

    @Column(unique = true)
    private String email;
    private String passdword;
    private LocalDateTime fechaNacimiento;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    //private Image imgPerfil;



}
