package com.salesianostriana.classmatic.servicios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AlumnoServicio<Alumno, Long, AlumnoRepositorio> {

    private final AlumnoRepositorio alumnoRepositorio;

}
