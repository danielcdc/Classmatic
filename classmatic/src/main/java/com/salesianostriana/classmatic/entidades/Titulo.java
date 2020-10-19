package com.salesianostriana.classmatic.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Titulo {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private String nivelAcademico;

    @OneToMany(mappedBy = "titulo")
    private List<Curso> cursos = new ArrayList();

    //Helpers curso
    public void addCurso(Curso c){
        cursos.add(c);
        c.setTitulo(this);
    }

    public void removeCurso(Curso c){
        cursos.remove(c);
        c.setTitulo(null);
    }




}
