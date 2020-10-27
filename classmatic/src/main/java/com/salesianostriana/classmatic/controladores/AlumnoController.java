package com.salesianostriana.classmatic.controladores;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Asignatura;
import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.HorarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alumno/")
public class AlumnoController {

    @Autowired
    AlumnoServicio alumnoServicio;

    @Autowired
    HorarioServicio horarioServicio;

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
    public String accederConvalidaciones(){
        return "alumno/alumnoConvalidaciones";
    }

    @GetMapping("/ampliaciones")
    public String accederAmpliacion(){
        return "alumno/alumnoAmpliaciones";
    }
}
