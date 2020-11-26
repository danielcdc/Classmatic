package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.*;
import com.salesianostriana.classmatic.repositorios.AlumnoRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class AlumnoServicio extends ServicioBaseImp<Alumno, Long, AlumnoRepositorio> {

    private final AlumnoRepositorio alumnoRepositorio;

    /**
     *  Determina el horario de un Alumno pasado como parámetro, con la finalidad de
     *  mostrar por pantalla el horario del Alumno.
     * @param al Una instancia de Alumno.
     */
    public List<List<Asignatura>> crearHorarioAlumno(Alumno al, HorarioServicio horarioServicio){
        List<Horario>listaHorarios=new ArrayList<Horario>();


        /* A través de las asignaturas que tiene un Alumno, se obtiene el horario de dichas asignaturas
         y se configura el horario del alumno.
         */
        for(Asignatura as : al.getAsignaturas()){
            listaHorarios.addAll(as.getHorarios());

        }
        /*
        Rellenamos el horario completo (de Lunes a Viernes con todas sus horas) el valor booleano False.
        El valor booleano False es para imprimir en la plantilla Thymeleaf.
         */
        List<List<Asignatura>>listaCompleta= new ArrayList();
        for(int i=0;i<5;i++){
            listaCompleta.add(new ArrayList<Asignatura>());
        }

        for(List l : listaCompleta){//relleno las 6 h de lo 5 d con falso
            for(int i=0;i<6;i++){
                l.add(false);
            }
        }
         /*
        Se comparan los mismos elementos de las listas "listaHorario" y "listaCompleta", y en aquellos que el alumno
        tenga un asignatura, se le asigna a "listaCompleta" el día y la hora en cuestión.
         */
        for(int i=0;i<5;i++){
            for(int j=0;j<6;j++){
                for(Horario h : listaHorarios){
                    if(h.getDia()==(i+1)){
                        for(int hora : h.getHoras()){
                            if(hora==(j+1)){
                                listaCompleta.get(i).set(j,h.getAsignatura());
                            }
                        }
                    }
                }
            }
        }
        return listaCompleta;
    }

    public List<Asignatura> sacarAsignaturas(Alumno a, AsignaturaServicio asignaturaServicio, CursoServicio cursoServicio){
        /*List<Asignatura>listaFinal=new ArrayList<Asignatura>();
        List<Asignatura>listaAsig=new ArrayList<Asignatura>();
        List<Curso>listaCursos=new ArrayList<Curso>();
        listaAsig.addAll(asignaturaServicio.findAll());


        listaCursos.addAll(cursoServicio.findAll());
        Long cursoId=a.getCurso().getId();

        for(Curso c : listaCursos){
            if(c.getId()==a.getCurso().getId()){
                for(Asignatura as : listaAsig){
                    System.out.println("--------------------------------------------------");
                    System.out.println(as.getNombre()+" "+as.getCurso().getNombre()+" "+as.getCurso().getId());
                    System.out.println("--------------------------------------------------");
                    if(as.getCurso().getId()==c.getId()){
                        listaFinal.add(as);
                    }
                }
            }
        }
        return listaFinal;*/
        return a.getAsignaturas();
    }

    public void solicitarConvalidacion(Long id, SituacionExcepcional situacionExcepcional,
                                       SituacionExcepcionalServicio situacionExcepcionalServicio){
        Alumno al=findById(id);
        al.addSituacionExcepcional(situacionExcepcional);
        edit(al);
        situacionExcepcionalServicio.edit(situacionExcepcional);
    }

    public void convalidar(AsignaturaServicio asignaturaServicio, SituacionExcepcionalServicio situacionExcepcionalServicio,
                           Long idAsignatura, SituacionExcepcional situacionExcepcional, Alumno alumno,
                           String ruta){
        System.out.println(idAsignatura+"------------------------------------------------------ID Asignatura");
        Asignatura as=asignaturaServicio.findById(idAsignatura);
        Alumno al=findById(alumno.getId());
        SituacionExcepcional sit = new SituacionExcepcional();
        sit.setArchivoConvalidacion(ruta);
        sit.setResuelta(false);
        sit.setFechaSolicitud(LocalDate.now());
        situacionExcepcionalServicio.save(sit);
        //situacionExcepcional.setArchivoConvalidacion(ruta);
        //situacionExcepcional.setResuelta(false);
        //situacionExcepcional.setFechaSolicitud(LocalDate.now());
        //situacionExcepcionalServicio.save(situacionExcepcional);
        al.addSituacionExcepcional(sit);
        //System.out.println(situacionExcepcional.getId()+"------------------------------------------------------ID Convalidacion");
        System.out.println(sit.getId()+"------------------------------------------------------ID Convalidacion");
        as.addSituacionExcepcional(sit);
        edit(al);
        asignaturaServicio.edit(as);
        situacionExcepcionalServicio.edit(sit);
    }



}
