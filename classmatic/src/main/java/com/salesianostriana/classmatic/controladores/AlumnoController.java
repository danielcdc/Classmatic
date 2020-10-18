package com.salesianostriana.classmatic.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alumno/")
public class AlumnoController {


    @GetMapping("/alumnoIni")
    public String inicioAlumno(){
        return "alumno/alumnoIni";
    }
}
