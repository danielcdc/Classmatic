package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.repositorios.AlumnoRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AlumnoServicio extends ServicioBaseImp<Alumno, Long, AlumnoRepositorio> {

    private final AlumnoRepositorio alumnoRepositorio;



}
