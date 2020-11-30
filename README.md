Carlos:
        -AlumnoServicio.crearHorarioAlumno(Alumno alumno, HorarioServicio horario)
        -ProfesorServicio.obtenerConvalidacionesPendientes(AlumnoServicio alumnoServicio, Long id)
        
Daniel:
        -AsignaturaServicio.obtenerHorarioAsignatura(Asignatura)
        -ProfesorServicio.negarConvalidacion(Long id, SituacionExcepcionalServicio  situacionExcepcionalServicio,
                                              AlumnoServicio alumnoServicio, AsignaturaServicio asignaturaServicio) 
        
Pablo:
        -UsuarioRepositorio.findByEmail(String email)
        -UsuarioRepositorio.findAll()
        -UsuarioRepositorio.findById()
        
Javi:
        -UsuarioServicio.aceptarValidacion(int codigo, String contrasenya)
        -UsuarioServicio.autogenerarCodigo()
        
