package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.*;
import com.salesianostriana.classmatic.repositorios.ProfesorRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ProfesorServicio extends ServicioBaseImp<Profesor,Long, ProfesorRepositorio> {

    private final ProfesorRepositorio profesorReporitorio;

    public void editarAlumno(Alumno a, Alumno al, AlumnoServicio alumnoServicio) {
        a.setNombre(al.getNombre());
        a.setApellidos(al.getApellidos());
        a.setEmail(al.getEmail());
        a.setFechaNacimiento(al.getFechaNacimiento());
        alumnoServicio.edit(a);

    }

    public void editarProfesor(Profesor p, Profesor pr, ProfesorServicio profesorServicio) {
        p.setNombre(pr.getNombre());
        p.setApellidos(pr.getApellidos());
        p.setEmail(pr.getEmail());
        p.setFechaNacimiento(pr.getFechaNacimiento());
        p.setEsJefe(pr.isEsJefe());
        profesorServicio.edit(p);

    }

    public void editarTitulo(Titulo t, Titulo tit, TituloServicio tituloServicio){
        t.setNombre(tit.getNombre());
        t.setNivelAcademico(tit.getNivelAcademico());
        tituloServicio.edit(t);
    }

    public void borrarTitulo(TituloServicio tituloServicio, CursoServicio cursoServicio,
                             AlumnoServicio alumnoServicio, AsignaturaServicio asignaturaServicio,
                             Long id){
        /*
        for(Curso curso : cursoServicio.findAll()){
            if(curso.getTitulo().getId()== id){
                //Desvincular alumnos y asignaturas
                for(Alumno a : alumnoServicio.findAll()){
                    if(a.getCurso().getId() == curso.getId()){
                        curso.removeAlumno(a);
                        alumnoServicio.edit(a);

                    }
                }
                for(Asignatura a : asignaturaServicio.findAll()){
                    if(a.getCurso().getId() == curso.getId()){
                        curso.removeAsignatura(a);
                        asignaturaServicio.edit(a);
                        for(Alumno al: alumnoServicio.findAll()){

                            /*for(Asignatura as : al.getAsignaturas()){
                                if(as.getId() == a.getId()){
                                    al.removeAsignatura(as);
                                }
                            }
                            for(int i=0;i<al.getAsignaturas().size();i++){
                                if(al.getAsignaturas().get(i).getId() == a.getId()){
                                    al.removeAsignatura(al.getAsignaturas().get(i));
                                    alumnoServicio.edit(al);
                                    asignaturaServicio.edit(al.getAsignaturas().get(i));
                                }
                            }
                        asignaturaServicio.delete(a);
                    }
                }
            }
            cursoServicio.delete(curso);*/
        /*if(!cursoServicio.findById(id).getAlumnos().isEmpty()){*/
            for(Alumno al : cursoServicio.findById(id).getAlumnos()){
                if(al.getCurso().getId() == cursoServicio.findById(id).getId()){
                    cursoServicio.findById(id).removeAlumno(al);
                    alumnoServicio.edit(al);
                }
            }
        //}

        /*if(!cursoServicio.findById(id).getAsignaturas().isEmpty()) {*/
            for (Asignatura as : cursoServicio.findById(id).getAsignaturas()) {
                for (Alumno al : as.getAlumnos()) {
                    al.removeAsignatura(as);
                    alumnoServicio.edit(al);
                }
                cursoServicio.findById(id).removeAsignatura(as);
                asignaturaServicio.edit(as);
                asignaturaServicio.delete(as);
            }
        //}
        cursoServicio.deleteById(id);
    }
}




