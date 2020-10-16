package com.salesianostriana.classmatic.repositorios;

import com.salesianostriana.classmatic.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor,Long> {
}
