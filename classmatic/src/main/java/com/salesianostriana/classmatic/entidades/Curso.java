package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

 @Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Curso {

    @Id @GeneratedValue
    private long id;

    private String nombre;

    @ManyToOne
    private Titulo titulo;

    @OneToMany(mappedBy = "curso")
    private List<Asignatura> asignaturas;

    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;

    //Helpers Asignatura

    public void addAsignatura(Asignatura a){
        asignaturas.add(a);
        a.setCurso(this);
    }

    public void removeAsignatura(Asignatura a){
        asignaturas.remove(a);
        a.setCurso(null);
    }

    //Helpers Alumno

    public void addAlumno(Alumno a){
        alumnos.add(a);
        a.setCurso(this);
    }

    public void removeAlumno(Alumno a){
        alumnos.remove(a);
        a.setCurso(null);
    }


}
