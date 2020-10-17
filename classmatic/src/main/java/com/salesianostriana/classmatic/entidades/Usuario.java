package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails {

    @Id @GeneratedValue
    private long id;


    private String nombre;
    private String apellidos;

    @Column(unique = true)
    private String email;
    private String passdword;
    private LocalDateTime fechaNacimiento;



    /*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

     */

    @Override
    public String getPassword() {
        return passdword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //private Image imgPerfil;



}
