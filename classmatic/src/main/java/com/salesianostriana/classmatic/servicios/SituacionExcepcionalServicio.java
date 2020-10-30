package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.SituacionExcepcional;
import com.salesianostriana.classmatic.repositorios.SituacionExcepcionalRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class SituacionExcepcionalServicio extends ServicioBaseImp<SituacionExcepcional, Long, SituacionExcepcionalRepositorio> {

    private final SituacionExcepcionalRepositorio situacionExcepcionalRepositorio;

    /*private void serAceptada(SituacionExcepcional situacionExcepcional, Long id){

    }*/



}
