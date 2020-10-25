package com.salesianostriana.classmatic.controladores;

import com.salesianostriana.classmatic.entidades.Alumno;
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
        //int codigo= 0;
        //model.addAttribute("codigo", codigo);
        //model.addAttribute("contrasenya", new String());
        //Alumno a=new Alumno();
        //model.addAttribute("contra",a.getPassdword());
        //model.addAttribute("codigo",a.getCodigoInvitacion());
        model.addAttribute("alumno",new Alumno());
        return "PrimerAcceso-2";
    }

    @PostMapping("/invitacion")
    public String validar(/*@ModelAttribute("codigo")int codigo,@ModelAttribute("contrasenya")String contrasenya*/
                            @ModelAttribute("alumno")Alumno alumno){
        System.out.println("La contrasenya es:"+alumno.getPassdword());
        usuarioServicio.aceptarValidacion(alumno.getCodigoInvitacion(),((Usuario)alumno).getPassdword());
        return this.iniciar();

    }

}
