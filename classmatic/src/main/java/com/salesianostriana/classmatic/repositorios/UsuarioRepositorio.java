package com.salesianostriana.classmatic.repositorios;

import com.salesianostriana.classmatic.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findFirstByEmail(String email);
}
