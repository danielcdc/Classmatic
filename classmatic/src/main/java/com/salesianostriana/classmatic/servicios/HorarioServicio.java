package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Horario;
import com.salesianostriana.classmatic.repositorios.HorarioRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioServicio extends ServicioBaseImp<Horario, Long, HorarioRepositorio> {

    private final HorarioRepositorio horarioRepositorio;

    public List<Integer> obtenerHoras(Horario horario){
        List<Integer>listaHorasHorario=new ArrayList<Integer>();
        listaHorasHorario.addAll(horario.getHoras());
        return listaHorasHorario;
    }
}
