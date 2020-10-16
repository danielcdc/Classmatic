package com.salesianostriana.classmatic.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ProfesorRepositorio<Profesor,Long,ProfesorRepositorio> {

    private final ProfesorRepositorio profesorReporitorio;


}
