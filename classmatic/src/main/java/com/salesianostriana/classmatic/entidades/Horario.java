package com.salesianostriana.classmatic.entidades;

import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"asignatura","horas"})
@ToString(exclude = {"asignatura","horas"})
public class Horario {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Asignatura asignatura;

    private int dia;

    @Column
    @ElementCollection(targetClass=Integer.class)//Si esto o detecta el tipo Integer
    //MappingException: Could not determine type for: java.util.List, at table: horario, for columns: [org.hibernate.mapping.Column(horas)]
    private List<Integer> horas;


    //Helper asignatura
    public void addAsignatura(Asignatura a){
        setAsignatura(a);
        a.getHorarios().add(this);
    }

    public void removeAsignatura(Asignatura a){
        setAsignatura(null);
        a.getHorarios().remove(this);
    }

    /*
    @JoinTable(
            joinColumns = @JoinColumn(name="curso_id"),
            inverseJoinColumns = @JoinColumn(name="horario_id")
    )*/
    /*@OneToOne(mappedBy = "horario")
    private Curso curso;*/

    /*
    @OneToMany(mappedBy = "horario")
    private List<Asignatura> hLunes;

    @OneToMany(mappedBy = "horario")
    private List<Asignatura> hMartes;

    @OneToMany(mappedBy = "horario")
    private List<Asignatura> hMiercoles;

    @OneToMany(mappedBy = "horario")
    private List<Asignatura> hJueves;

    @OneToMany(mappedBy = "horario")
    private List<Asignatura> hViernes;
    */


}
