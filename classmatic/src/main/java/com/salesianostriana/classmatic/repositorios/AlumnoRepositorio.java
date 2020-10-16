package com.salesianostriana.classmatic.repositorios;

import com.salesianostriana.classmatic.entidades.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {
}
