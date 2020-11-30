package com.salesianostriana.classmatic.entidades;

import lombok.*;
import org.hibernate.annotations.Fetch;


import javax.persistence.*;
import java.util.List;

/**
 * Contiene las horas de un día concreto de una asignatura concreta.
 */
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
    @ElementCollection(targetClass=Integer.class, fetch = FetchType.EAGER)
    //Si esto o detecta el tipo Integer
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
}
