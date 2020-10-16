package com.salesianostriana.classmatic.repositorios;

import com.salesianostriana.classmatic.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
}
