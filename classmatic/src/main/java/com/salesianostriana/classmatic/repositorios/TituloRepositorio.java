package com.salesianostriana.classmatic.repositorios;

import com.salesianostriana.classmatic.entidades.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloRepositorio extends JpaRepository<Titulo,Long> {
}
