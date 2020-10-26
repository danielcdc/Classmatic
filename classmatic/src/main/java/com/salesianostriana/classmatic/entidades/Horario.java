package com.salesianostriana.classmatic.entidades;

import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"curso"})
@ToString(exclude = {"curso"})
public class Horario {

    @Id
    @GeneratedValue
    private Long id;


    /*
    @JoinTable(
            joinColumns = @JoinColumn(name="curso_id"),
            inverseJoinColumns = @JoinColumn(name="horario_id")
    )*/
    @OneToOne(mappedBy = "horario")
    private Curso curso;

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

}
