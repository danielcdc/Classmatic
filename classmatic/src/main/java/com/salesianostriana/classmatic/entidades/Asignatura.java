package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Asignatura {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private int nHorasSemanales;

    @ManyToMany(mappedBy = "asignaturas")
    private List<Alumno> alumnos;

    @ManyToOne
    private Horario horario;


    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "asignatura")
    private List<SituacionExcepcional> situacionExc;

    @OneToMany(mappedBy = "asignatura")
    private List<SolicitudAmpliacionMatricula>solicitudesAmp;

    //Helpers situacionesExcepcionales

    public void addSituacionExcepcional(SituacionExcepcional s){
        situacionExc.add(s);
        s.setAsignatura(this);
    }

    public void removeSituacionExcepcional(SituacionExcepcional s){
        situacionExc.remove(s);
        s.setAsignatura(null);
    }




}
