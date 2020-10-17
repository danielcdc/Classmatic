package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Curso;
import com.salesianostriana.classmatic.repositorios.CursoRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServicio extends ServicioBaseImp<Curso,Long, CursoRepositorio> {

    private final CursoRepositorio cursoRepositorio;

}
