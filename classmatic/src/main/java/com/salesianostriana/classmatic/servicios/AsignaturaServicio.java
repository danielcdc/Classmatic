package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Asignatura;
import com.salesianostriana.classmatic.repositorios.AsignaturaRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AsignaturaServicio extends ServicioBaseImp<Asignatura,Long, AsignaturaRepositorio> {

    private final AsignaturaRepositorio asignaturaRepositorio;

}
