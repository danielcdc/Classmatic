package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.salesianostriana.classmatic.repositorios.UsuarioRepositorio;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class UsuarioServicio extends ServicioBaseImp <Usuario, Long, UsuarioRepositorio> {

    private final UsuarioRepositorio usuarioRepositorio;

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepositorio.findFirstByEmail(email);
    }


}
