package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    //private Curso curso;




}
