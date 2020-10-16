package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Alumno extends Usuario {

    @ManyToOne
    private Curso curso;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name="alumno_id"),
            inverseJoinColumns = @JoinColumn(name="asignatura_id")
    )
    private List<Asignatura> asignaturas;

    @ManyToOne
    private List<SituacionExcepcional> situacionesExc;

    @ManyToOne
    private List<SolicitudAmpliacionMatricula> solicitudesAmp;

    //Helpers

    public void addAsignatura(Asignatura a){
        asignaturas.add(a);
        a.getAlumnos().add(this);
    }

    public void removeAsignatura(Asignatura a){
        asignaturas.remove(a);
        a.getAlumnos().remove(this);
    }



}
