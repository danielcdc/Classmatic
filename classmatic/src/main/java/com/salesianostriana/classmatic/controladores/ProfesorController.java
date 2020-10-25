package com.salesianostriana.classmatic.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

    @GetMapping("/profesorIni")
    public String accederAlInicio(){
        return "profesor/profesorIni";
    }

}
