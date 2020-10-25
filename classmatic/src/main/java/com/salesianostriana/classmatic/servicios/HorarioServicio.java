package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Horario;
import com.salesianostriana.classmatic.repositorios.HorarioRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HorarioServicio extends ServicioBaseImp<Horario, Long, HorarioRepositorio> {

    private final HorarioRepositorio horarioRepositorio;
}
