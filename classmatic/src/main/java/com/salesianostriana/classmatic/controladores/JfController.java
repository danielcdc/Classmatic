package com.salesianostriana.classmatic.controladores;

import com.salesianostriana.classmatic.entidades.*;
import com.salesianostriana.classmatic.servicios.*;
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

    @Autowired
    CursoServicio cursoServicio;

    @Autowired
    AsignaturaServicio asignaturaServicio;

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

    //Anyadir Titulo
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

    //Editar Titulo
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

    //Eliminar Titulo
    @GetMapping("/adminTitulos/eliminarTitulo/{id}")
    public String eliminarTitulo(@PathVariable Long id, Model model){
        //tituloServicio.delete(tituloServicio.findById(id));
        /*
        * Crear método borrar titulo en profesorServicio que borre en cascada los elementos que compongan
        * el título a borrar
        * */
        profesorServicio.borrarTitulo(tituloServicio, cursoServicio, alumnoServicio, asignaturaServicio, id );
        return accederTitulos( model);
    }

    //Visualizacion de Cçcursos de TituloX
    @GetMapping("/adminTitulos/adminCursos/{id}")
    public String accederCursos(@PathVariable Long id, Model model){
        model.addAttribute("cursos", tituloServicio.findById(id).getCursos());
        model.addAttribute("idTitulo",id);
        model.addAttribute("nombreTitulo",tituloServicio.findById(id).getNombre());
        return "jf/adminCursos";
    }

    //Anyadir Curso a un titulo
    @GetMapping("/adminTitulos/adminCursos/addCurso/{id}")
    public String accederCrearAddCurso(@PathVariable Long id, Model model){
        model.addAttribute("curso", new Curso());
        model.addAttribute("idTitulo", id);
        return "jf/adminAddCurso";
    }

    @PostMapping("/adminTitulos/adminCursos/addCurso/{id}")
    public String crearCurso(@ModelAttribute("curso")Curso curso, @PathVariable Long id, Model model){
        /*cursoServicio.save(curso);
        tituloServicio.findById(id).addCurso(curso);
        cursoServicio.edit(curso);*/
        profesorServicio.anyadirCurso(cursoServicio, tituloServicio, curso, id);
        return accederCursos( id, model);
    }

    //Editar Curso
    @GetMapping("/adminCursos/editCurso/{id}")
    public String accederEditarCurso(@PathVariable Long id, Model model){
        model.addAttribute("curso",cursoServicio.findById(id));
        return "jf/adminModificarCurso";
    }

    @PostMapping("/adminCursos/editCurso/{id}")
    public String modificarCurso(@ModelAttribute("curso")Curso curso, Model model, @PathVariable Long id){
        profesorServicio.editarCurso(cursoServicio.findById(id), curso, cursoServicio);
        return accederCursos(cursoServicio.findById(id).getTitulo().getId(),  model);
    }

    //Eliminar Curso

    @GetMapping("/eliminarCurso/{id}")
    public String eliminarCurso(@PathVariable Long id, Model model){
        //Long idT=profesorServicio.eliminarCurso(cursoServicio.findById(id), asignaturaServicio, alumnoServicio, cursoServicio);
        return accederCursos( profesorServicio.eliminarCurso(cursoServicio.findById(id), asignaturaServicio, alumnoServicio, cursoServicio),  model);
    }

    //Accedr a listado asignaturas
    @GetMapping("/adminAsignnaturas{id}")
    public String accederAsignaturas(@PathVariable Long id, Model model){
        Curso c=cursoServicio.findById(id);
        model.addAttribute("asignaturas",c.getAsignaturas());
        model.addAttribute("nombreCurso",c.getNombre());
        model.addAttribute("idCurso",id);
        model.addAttribute("idTitulo", c.getTitulo().getId());
        model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
        return "jf/adminAsignaturas";
    }

    //Eliminar Asignatura
    @GetMapping("/adminAsignaturas/eliminarAsignatura/{id}")
    public String eliminarAsinatura(@PathVariable Long id, Model model){
        return accederAsignaturas(profesorServicio.eliminarAsignatura(id, asignaturaServicio, cursoServicio, alumnoServicio), model);
    }

    //Anyadir asignatura
    @GetMapping("/adminAsignaturas/addAsignatura/{id}")
    public String accederAddAsignatura(@PathVariable Long id, Model model){
        Curso c=cursoServicio.findById(id);
        model.addAttribute("asignatura", new Asignatura());
        model.addAttribute("idCurso",id);
        model.addAttribute("nombreCurso",c.getNombre());
        model.addAttribute("idTitulo",c.getTitulo().getId());
        return "jf/adminAddAsignatura";
    }

    @PostMapping("/adminAsignaturas/addAsignatura/{id}")
    public String crearAsignatura(@ModelAttribute("asignatura")Asignatura asignatura, Model model, @PathVariable Long id){
        profesorServicio.crearAsignatura(id, asignatura, asignaturaServicio, cursoServicio);
        return accederAsignaturas( id,  model);
    }

    //Modificar Asignatura
    @GetMapping("/adminAsignaturas/editAsignatura/{id}")
    public String accederEditarAsignatura(@PathVariable Long id, Model model){
        model.addAttribute("asignatura",asignaturaServicio.findById(id));
        return "jf/adminModificarAsignatura";
    }

    @PostMapping("/adminAsignaturas/editAsignatura/{id}")
    public String editarAsignatura(@ModelAttribute("asignatura")Asignatura asignatura,Model model,
                                   @PathVariable Long id){
        profesorServicio.editarAsignatura(id, asignaturaServicio, asignatura);
        return accederAsignaturas(asignaturaServicio.findById(id).getCurso().getId(),  model);
    }

    /*
    @GetMapping("/adminAsignnaturas{id}")
    public String accederAsignaturas(@PathVariable Long id, Model model){
        Curso c=cursoServicio.findById(id);
        model.addAttribute("asignaturas",c.getAsignaturas());
        model.addAttribute("nombreCurso",c.getNombre());
        model.addAttribute("idCurso",id);
        model.addAttribute("idTitulo", c.getTitulo().getId());
        model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
        return "jf/adminAsignaturas";
    }
     */

    //Acceder a alumnos de una clase
    @GetMapping("/adminAlumnos/accederAlumnos/{id}")
    public String accederAlumnos(@PathVariable Long id, Model model){
        Curso c=cursoServicio.findById(id);
        model.addAttribute("alumnos",c.getAlumnos());
        model.addAttribute("idCurso",id);
        model.addAttribute("nombreCurso", c.getNombre());
        model.addAttribute("idTitulo",c.getTitulo().getId());
        model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
        return "jf/adminCursoAlumnos";
    }

}
