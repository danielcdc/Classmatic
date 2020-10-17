package com.salesianostriana.classmatic.repositorios;

import com.salesianostriana.classmatic.entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {
}
