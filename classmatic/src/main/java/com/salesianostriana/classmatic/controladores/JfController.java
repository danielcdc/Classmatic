package com.salesianostriana.classmatic.controladores;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Profesor;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jf/")
public class JfController {

    @Autowired
    AlumnoServicio alumnoServicio;

    @Autowired
    ProfesorServicio profesorServicio;

    @GetMapping("/adminInicio")
    public String iniciarAdmin(){
        return "jf/adminInicio";
    }

    //Visualizacion de lista alumnos
    @GetMapping("/adminAlumnos")
    public String accederAlumnos(Model model){
        model.addAttribute("alumnos",alumnoServicio.findAll());
        return "jf/adminAlumnos";
    }

    //Visualizacion de lista profesores
    @GetMapping("/adminProfesores")
    public String accederProfesores(Model model){
        model.addAttribute("profesores",profesorServicio.findAll());
        return "jf/adminProfesores";
    }

    //Eliminar Alumno de lista alumnos
    @GetMapping("/adminAlumnos/eliminarAlumno/{id}")
    public String eliminarUsuarioAlumno(Model model,@PathVariable Long id){
        alumnoServicio.deleteById(id);
        return accederAlumnos(model);
    }

    //Eliminar Profesor de lista profesores
    @GetMapping("/adminProfesores/eliminarProfesor/{id}")
    public String eliminarUsuarioProfesor(Model model, @PathVariable Long id){
        profesorServicio.deleteById(id);
        return accederProfesores(model);
    }

    //Anyadir Alumno
    @GetMapping("/adminAlumnos/addAlumno")
    public String accederAddAlumno(Model model){
        model.addAttribute("alumnoForm",new Alumno());
        return "jf/adminAddAlumno";
    }

    @PostMapping("/adminAlumnos/addAlumno")
    public String crearAlumno(@ModelAttribute("alumnoForm")Alumno alumno, Model model){
        alumnoServicio.save(alumno);
        return accederAlumnos(model);
    }

    //Anydir profesor
    @GetMapping("/adminProfesores/addProfesor")
    public String accederAddProfesor(Model model){
        model.addAttribute("profesorForm",new Profesor());
        return "jf/adminAddProfesor";
    }

    @PostMapping("/adminProfesores/addProfesor")
    public String crearProfesor(@ModelAttribute("profesorForm")Profesor profesor, Model model){
        profesorServicio.save(profesor);
        return accederProfesores( model);
    }

}
