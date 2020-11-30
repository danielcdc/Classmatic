package com.salesianostriana.classmatic.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Asignatura {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private int nHorasSemanales;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "asignaturas")
    private List<Alumno> alumnos;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.EAGER)
    private List<Horario> horarios;


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
