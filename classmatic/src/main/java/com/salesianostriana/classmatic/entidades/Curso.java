package com.salesianostriana.classmatic.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
 @EqualsAndHashCode(exclude={"titulo","asignaturas","alumnos","horario"})
@ToString(exclude = {"titulo","asignaturas","alumnos","horario"})
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

    @OneToOne
    private Horario horario;

    //Helpers Horario
    public void addHorario(Horario h){
        this.setHorario(h);
        h.setCurso(this);
    }
    public void removeHorario(Horario h){
        this.setHorario(null);
        h.setCurso(null);
    }


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
