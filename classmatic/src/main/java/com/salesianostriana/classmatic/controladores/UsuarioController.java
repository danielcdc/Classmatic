package com.salesianostriana.classmatic.controladores;

import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String iniciar(){
        return "Login1-3";
    }

    @GetMapping("/login")
    public String iniciar2(){
        return "Login1-3";
    }


    @GetMapping("/invitacion")
    public String aceptarInvitacion(Model model){
        Integer codigo=0;
        model.addAttribute("codigo", codigo);
        model.addAttribute("contrasenya", new String());
        return "PrimerAcceso-2";
    }

    @PostMapping()
    public String validar(@ModelAttribute("codigo")Integer codigo,@ModelAttribute("contrasenya")String contrasenya){
        usuarioServicio.aceptarValidacion(codigo,contrasenya);
        return iniciar();

    }

}
