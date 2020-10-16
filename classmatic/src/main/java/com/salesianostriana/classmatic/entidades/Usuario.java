package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.*;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String passdword;
    private LocalDateTime fechaNacimiento;

    //private Image imgPerfil;



}
