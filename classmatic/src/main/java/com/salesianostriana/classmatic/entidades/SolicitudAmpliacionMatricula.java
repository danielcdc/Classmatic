package com.salesianostriana.classmatic.entidades;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SolicitudAmpliacionMatricula {

    @Id @GeneratedValue
    private long id;

    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaResolucion;
    private String estado;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Asignatura asignatura;





}
