package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SituacionExcepcional {

    @Id @GeneratedValue
    private long id;

    private LocalDate fechaSolicitud;
    //private String adjunto;  --> Usar tipo File ¿?
    private LocalDate fechaResolucion;
    private boolean resuelta;
    private String  archivoConvalidacion;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Asignatura asignatura;



}
