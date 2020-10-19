package com.salesianostriana.classmatic.entidades;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class Alumno extends Usuario {

    @ManyToOne
    private Curso curso;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name="alumno_id"),
            inverseJoinColumns = @JoinColumn(name="asignatura_id")
    )

    private List<Asignatura> asignaturas;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "alumno")
    private List<SituacionExcepcional> situacionesExc;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "alumno")
    private List<SolicitudAmpliacionMatricula> solicitudesAmp;

    //Helpers asignaturas

    public void addAsignatura(Asignatura a){
        asignaturas.add(a);
        a.getAlumnos().add(this);
    }

    public void removeAsignatura(Asignatura a){
        asignaturas.remove(a);
        a.getAlumnos().remove(this);
    }

    //Helpers situacionesExcepcionales

    public void addSituacionExcepcional(SituacionExcepcional s){
        situacionesExc.add(s);
        s.setAlumno(this);
    }

    public void removeSituacionExcepcional(SituacionExcepcional s){
        situacionesExc.remove(s);
        s.setAlumno(null);
    }

    //Helpers solicitudesAmpliacionMatricula

    public void addSolicitudAmpliacionMatricula(SolicitudAmpliacionMatricula s){
        solicitudesAmp.add(s);
        s.setAlumno(this);
    }

    public void removeSolicitudAmpliacionMatricula(SolicitudAmpliacionMatricula s){
        solicitudesAmp.add(s);
        s.setAlumno(this);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ALUMNO"));
    }

    @Builder
    public Alumno(long id, String nombre, String apellidos, String email, String passdword, LocalDate/*Time*/ fechaNacimiento, Curso curso, List<Asignatura> asignaturas, List<SituacionExcepcional> situacionesExc, List<SolicitudAmpliacionMatricula> solicitudesAmp) {
        super(id, nombre, apellidos, email, passdword, fechaNacimiento);
        this.curso = curso;
        this.asignaturas = asignaturas;
        this.situacionesExc = situacionesExc;
        this.solicitudesAmp = solicitudesAmp;
    }
}
