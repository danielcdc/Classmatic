package com.salesianostriana.classmatic.entidades;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Profesor extends Usuario {

    private boolean esJefe;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String rol;
        if(esJefe){
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_JF"));
        }else{
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_PROFESOR"));
        }
    }

    @Builder
    public Profesor(long id, String nombre, String apellidos, String email, String passdword, LocalDate/*Time*/ fechaNacimiento, boolean esJefe) {
        super(id, nombre, apellidos, email, passdword, fechaNacimiento);
        this.esJefe = esJefe;
    }
}
