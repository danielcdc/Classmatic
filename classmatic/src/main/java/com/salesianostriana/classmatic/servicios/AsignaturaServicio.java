package com.salesianostriana.classmatic.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AsignaturaServicio<Asignatura,Long,AsignaturaRepositorio> {

    private final AsignaturaRepositorio asignaturaRepositorio;

}
