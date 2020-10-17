package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.SolicitudAmpliacionMatricula;
import com.salesianostriana.classmatic.repositorios.SolicitudAmpliacionMatriculalRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class SolicitudAmpliacionMatriculaServicio extends ServicioBaseImp<SolicitudAmpliacionMatricula,
        Long, SolicitudAmpliacionMatriculalRepositorio> {

    private final SolicitudAmpliacionMatriculalRepositorio solicitudAmpliacionMatriculalRepositorio;


}
