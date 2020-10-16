package com.salesianostriana.classmatic.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SolicitudAmpliacionMatricula {

    @Id @GeneratedValue
    private long id;

    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaResolucion;
    private String estado;

    @OneToMany(mappedBy = "solicitudesAmp")
    private Alumno alumno;

    @OneToMany(mappedBy = "solicitudesAmp")
    private Asignatura asignatura;





}
