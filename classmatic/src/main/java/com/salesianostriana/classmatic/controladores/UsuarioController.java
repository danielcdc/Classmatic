package com.salesianostriana.classmatic.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {


    @GetMapping("/")
    public String iniciar(){
        return "Login1-3";
    }

    /*
    @GetMapping("/invitacion")
    public String aceptarInvitacion(){
        return "PrimerAcceso-2";
    }
    */
}
