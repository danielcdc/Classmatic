package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SituacionExcepcional {

    @Id @GeneratedValue
    private long id;

    private LocalDateTime fechaSolicitud;
    private String tipo;
    //private String adjunto;  --> Usar tipo File ¿?
    private LocalDateTime fechaResolucion;
    private  String estado;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Asignatura asignatura;



}
