package com.salesianostriana.classmatic.repositorios;

import com.salesianostriana.classmatic.entidades.Curso;
import com.salesianostriana.classmatic.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepositorio extends JpaRepository<Horario, Long> {
}
