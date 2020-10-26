package com.salesianostriana.classmatic.entidades;

import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"asignatura"})
@ToString(exclude = {"asignatura"})
public class Horario {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "horario")
    private Asignatura asignatura;

    //El Horario es de cada asignatura, por lo tanto guardará una lista que a su vez guardará cinco listas,
    //una por cada día que contendrá seis espacios con booleanos,
    //true si tiene ese día a esa hora, false de lo contrario
    private List < List<Boolean> > horas;

    //Helper asignatura
    public void addAsignatura(Asignatura a){
        setAsignatura(a);
        a.setHorario(this);
    }

    public void removeAsignatura(Asignatura a){
        setAsignatura(null);
        a.setHorario(null);
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
