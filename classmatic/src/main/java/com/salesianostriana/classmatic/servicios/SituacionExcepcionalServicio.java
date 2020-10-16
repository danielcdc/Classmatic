package com.salesianostriana.classmatic.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class SituacionExcepcionalServicio<SituacionExcepcional, Long, SituacionExcepcionalRepositorio> {

    private final SituacionExcepcionalRepositorio situacionExcepcionalRepositorio;



}
