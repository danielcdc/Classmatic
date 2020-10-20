package com.salesianostriana.classmatic.controladores;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Profesor;
import com.salesianostriana.classmatic.entidades.Titulo;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.ProfesorServicio;
import com.salesianostriana.classmatic.servicios.TituloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/jf/")
public class JfController {

    @Autowired
    AlumnoServicio alumnoServicio;

    @Autowired
    ProfesorServicio profesorServicio;

    @Autowired
    TituloServicio tituloServicio;

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

    //Editar alumno
    @GetMapping("/adminAlumnos/editarAlumno/{id}")
    public String accederEditarAlumno(@PathVariable Long id, Model model){
        model.addAttribute("alumno",alumnoServicio.findById(id));
        return "jf/modificarAlumno";
    }

    @PostMapping("/adminAlumnos/editarAlumno/{id}")
    public String modificarAlumno(@PathVariable Long id, @ModelAttribute("alumno")Alumno alumno, Model model){
        profesorServicio.editarAlumno(alumnoServicio.findById(id),alumno, alumnoServicio);
        //alumnoServicio.edit(alumnoServicio.findById(id));
        return accederAlumnos( model);
    }

    //Editar profesor
    @GetMapping("/adminProfesores/modificarProfesor/{id}")
    public String accederModificarProfesor(@PathVariable Long id, Model model){
        model.addAttribute("profesor", profesorServicio.findById(id));
        return "jf/adminModificarProfesor";
    }

    @PostMapping("/adminProfesores/modificarProfesor/{id}")
    public String modificarProfesor(@PathVariable Long  id, Model model, @ModelAttribute("profesor")Profesor profesor){
        profesorServicio.editarProfesor(profesorServicio.findById(id),profesor, profesorServicio);
        //profesorServicio.edit(profesorServicio.findById(id));
        return accederProfesores( model);
    }

    //Visualizacion Titulos
    @GetMapping("/adminTitulos")
    public String accederTitulos(Model model){
        model.addAttribute("titulos",tituloServicio.findAll());
        return "jf/adminTitulos";
    }

    @GetMapping("/adminTitulos/addTitulo")
    public String accederAddTitulo(Model model){
        model.addAttribute("titulo",new Titulo());
        return "jf/adminAddTitulo";
    }

    @PostMapping("/adminTitulos/addTitulo")
    public String addTitulos(@ModelAttribute("titulo")Titulo titulo, Model model){
        tituloServicio.save(titulo);
        return accederTitulos(model);
    }

    @GetMapping("/adminTitulos/editTitulo/{id}")
    public String accederEditarTitulo(Model model, @PathVariable Long id){
        model.addAttribute("titulo", tituloServicio.findById(id));
        return "jf/adminModificarTitulo";
    }

    @PostMapping("/adminTitulos/editTitulo/{id}")
    public String modificarTitulo(@ModelAttribute("titulo")Titulo titulo,Model  model, @PathVariable Long id){
        profesorServicio.editarTitulo(tituloServicio.findById(id),titulo, tituloServicio);
        return accederTitulos(model);
    }

}
