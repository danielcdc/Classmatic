package com.salesianostriana.classmatic.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UsuarioServicio<Usuario,Long,UsuarioRepositorio> {

    private final UsuarioRepositorio usuarioRepositorio;

}
