package com.salesianostriana.classmatic.repositorios;

import com.salesianostriana.classmatic.entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
