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

    @OneToOne(mappedBy = "horario")
    @JoinTable(
            name = "curso_horario",
            joinColumns = @JoinColumn(name="curso_id"),
            inverseJoinColumns = @JoinColumn(name="horario_id")
    )
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
