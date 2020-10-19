package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Profesor;
import com.salesianostriana.classmatic.repositorios.ProfesorRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ProfesorServicio extends ServicioBaseImp<Profesor,Long, ProfesorRepositorio> {

    private final ProfesorRepositorio profesorReporitorio;

    public void editarAlumno(Alumno a, Alumno al) {
        a.setNombre(al.getNombre());
        a.setApellidos(al.getApellidos());
        a.setEmail(al.getEmail());
        a.setFechaNacimiento(al.getFechaNacimiento());

    }

    public void editarProfesor(Profesor p, Profesor pr) {
        p.setNombre(pr.getNombre());
        p.setApellidos(pr.getApellidos());
        p.setEmail(pr.getEmail());
        p.setFechaNacimiento(pr.getFechaNacimiento());
        p.setEsJefe(pr.isEsJefe());

    }


}
