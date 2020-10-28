package com.salesianostriana.classmatic.controladores;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Asignatura;
import com.salesianostriana.classmatic.entidades.SituacionExcepcional;
import com.salesianostriana.classmatic.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alumno/")
public class AlumnoController {

    @Autowired
    AlumnoServicio alumnoServicio;

    @Autowired
    HorarioServicio horarioServicio;

    @Autowired
    AsignaturaServicio asignaturaServicio;

    @Autowired
    CursoServicio cursoServicio;

    /*@Autowired
    StorageService storageService;*/

    @Autowired
    SituacionExcepcionalServicio situacionExcepcionalServicio;

    /*@GetMapping("/alumnoIni")
    public String inicioAlumno(){
        return "alumno/alumnoIni";
    }*/


    @GetMapping("/alumnoIni")
    public String accederHorario(@AuthenticationPrincipal Alumno alumno, Model model){
        List<List<Asignatura>>listaCompleta=new ArrayList<List<Asignatura>>();
        listaCompleta=alumnoServicio.crearHorarioAlumno(alumno, horarioServicio);
        model.addAttribute("horario",listaCompleta);
        return "alumno/alumnoHorario";
    }

    @GetMapping("/convalidaciones")
    public String accederConvalidaciones(@AuthenticationPrincipal Alumno alumno, Model model){
        List<Asignatura>asignaturas;
        asignaturas=alumnoServicio.sacarAsignaturas(alumno, asignaturaServicio, cursoServicio);
        model.addAttribute("asignaturas",asignaturas);
        return "alumno/alumnoConvalidaciones";
    }

/*
    @GetMapping("/convalidacionde/{id}")
    public String convalidarAsignatura(@PathVariable Long id, Model model, @AuthenticationPrincipal Alumno alumno){
        //SituacionExcepcional sit=new SituacionExcepcional();
        *//*sit.setAsignatura(asignaturaServicio.findById(id));
        sit.setFechaSolicitud(LocalDate.now());
        sit.setResuelta(false);
        sit.setAlumno(alumno);
        sit.setFechaResolucion(null);*//*
        *//*System.out.println("Ha entrado en la peticion de la convalidacion de una asignatura..................................................");
        model.addAttribute("convalidacion", new SituacionExcepcional());
        System.out.println("Ha anyadido la SituacionExcepcional al model..................................................");
        model.addAttribute("asignatura",asignaturaServicio.findById(id));
        System.out.println("Ha anyadido la asignatura  al model..................................................");*//*
        return "alumno/alumnoConvalidar";
    }


    @PostMapping("/convalidacionde/{id}")
    public String convalidar(*//*@RequestParam("archivo") MultipartFile archivo,*//* @PathVariable Long id,
                             @ModelAttribute("convalidacion")SituacionExcepcional situacionExcepcional,
                             @AuthenticationPrincipal Alumno alumno,
                             Model model){
        *//*if(archivo.isEmpty()){
            return convalidarAsignatura( id,  model,  alumno);
        }else{
            //implementar tratamiento de fichero
            String rutaArchivo = storageService.store(archivo, situacionExcepcional.getId());
            situacionExcepcional.setArchivoConvalidacion(MvcUriComponentsBuilder
                .fromMethodName(AlumnoController.class, "serverFile", rutaArchivo)
                .build().toUriString());
            situacionExcepcionalServicio.edit(situacionExcepcional);
            return accederConvalidaciones( alumno,  model);
        }*//*
        //alumnoServicio.solicitarConvalidacion(id, situacionExcepcional, situacionExcepcionalServicio);
        return accederConvalidaciones( alumno,  model);
    }*/

    @GetMapping("/convalidarAsignatura/{id}")
    public String convalidarAsignatura(@PathVariable Long id, Model model){
        model.addAttribute("asignatura", asignaturaServicio.findById(id));
        //model.addAttribute()
        return "alumno/convalidarAsignatura";
    }


    @GetMapping("/ampliaciones")
    public String accederAmpliacion(){
        return "alumno/alumnoAmpliaciones";
    }
}
