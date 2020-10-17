package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Titulo;
import com.salesianostriana.classmatic.repositorios.TituloRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class TituloServicio extends ServicioBaseImp<Titulo,Long, TituloRepositorio> {

    private final TituloRepositorio tituloRepositorio;

}
