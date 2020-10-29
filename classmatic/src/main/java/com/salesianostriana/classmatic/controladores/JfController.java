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

    @Autowired
    EnvioEmailServicio envioEmailServicio;

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    SituacionExcepcionalServicio situacionExcepcionalServicio;



    @GetMapping("/adminInicio")
    public String iniciarAdmin(){
        return "jf/adminInicio";
    }

    //Visualizacion de lista alumnos
    @GetMapping("/adminAlumnos")
    public String accederAlumnos(Model model){
        model.addAttribute("alumnos",alumnoServicio.findAll());
        /*Alumno alu=new Alumno();
        alu.setEmail("cgl76490@gmail.com");
        envioEmailServicio.sendEmail(alu,"pruebaSpringMail","Ha funcionado el envio");*///Esto era una prueba de envio de email
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
        //alumnoServicio.deleteById(id);
        profesorServicio.eliminarAlumno(alumnoServicio, asignaturaServicio, cursoServicio,id);
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
        model.addAttribute("cursos",cursoServicio.findAll());
        return "jf/adminAddAlumno";
    }

    @PostMapping("/adminAlumnos/addAlumno")//-----------------------------------------------------IMPLEMENTAR VALIDACION
    public String crearAlumno(@ModelAttribute("alumnoForm")Alumno alumno, Model model){
        //alumnoServicio.save(alumno);
        profesorServicio.anyadirAlumno(alumno, alumnoServicio, cursoServicio, usuarioServicio, envioEmailServicio);
        return accederAlumnos(model);
    }

    //Anydir profesor
    @GetMapping("/adminProfesores/addProfesor")
    public String accederAddProfesor(Model model){
        model.addAttribute("profesorForm",new Profesor());
        return "jf/adminAddProfesor";
    }

    @PostMapping("/adminProfesores/addProfesor")//------------------------------------------------IMPLEMENTAR VALIDACION
    public String crearProfesor(@ModelAttribute("profesorForm")Profesor profesor, Model model){
        //profesorServicio.save(profesor);
        profesorServicio.anyadirProfesor(new Profesor(),profesor, usuarioServicio, envioEmailServicio);
        return accederProfesores( model);
    }

    //Editar alumno -----------------------------------------------------------------Mirar para Modificar y anyadir alumno con curso
    @GetMapping("/adminAlumnos/editarAlumno/{id}")
    public String accederEditarAlumno(@PathVariable Long id, Model model){
        Alumno al=alumnoServicio.findById(id);
        model.addAttribute("alumno",al);
        model.addAttribute("cursos",cursoServicio.findAll());
        return "jf/modificarAlumno";
    }

    @PostMapping("/adminAlumnos/editarAlumno/{id}")
    public String modificarAlumno(@PathVariable Long id,
                                  @ModelAttribute("alumno")Alumno alumno,
                                  Model model){

        profesorServicio.editarAlumno(alumnoServicio.findById(id),alumno,
                alumnoServicio, cursoServicio);

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
        profesorServicio.editarProfesor(profesorServicio.findById(id),profesor);
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
        Curso c=cursoServicio.findById(id);
        model.addAttribute("idTitulo",c.getTitulo().getId());
        model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
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
        model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
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
        Asignatura as=asignaturaServicio.findById(id);
        model.addAttribute("asignatura",as);
        model.addAttribute("nombreCurso", as.getCurso().getNombre());
        model.addAttribute("idCurso", as.getCurso().getId());
        model.addAttribute("idTitulo",as.getCurso().getTitulo().getId());
        model.addAttribute("nombreTitulo",as.getCurso().getTitulo().getNombre());
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

    //Eliminar alumno desde curso
    @GetMapping("/adminAlumnos/eliminarAlumnoDeCurso/{id}")
    public String eliminarAlumnoDeCurso(@PathVariable Long id, Model model){
        Long idCurso=alumnoServicio.findById(id).getCurso().getId();
        profesorServicio.eliminarAlumno(alumnoServicio, asignaturaServicio, cursoServicio, id);
        return accederAlumnos(idCurso,  model);
    }
/*
    //Anyadir Alumno
    @GetMapping("/adminAlumnos/addAlumno")
    public String accederAddAlumno(Model model){
        model.addAttribute("alumnoForm",new Alumno());
        model.addAttribute("cursos",cursoServicio.findAll());
        return "jf/adminAddAlumno";
    }

    @PostMapping("/adminAlumnos/addAlumno")
    public String crearAlumno(@ModelAttribute("alumnoForm")Alumno alumno, Model model){
        //alumnoServicio.save(alumno);
        profesorServicio.anyadirAlumno(alumno, alumnoServicio, cursoServicio);
        return accederAlumnos(model);
    }
*/
    //Anyadir alumno a curso
    @GetMapping("/adminAlumnosCurso/addAlumnoACurso/{id}")//--------------------------------------IMPLEMENTAR VALIDACION
    public String addAlumnoAAcurso(@PathVariable Long id, Model model){
        Curso c=cursoServicio.findById(id);
        model.addAttribute("idCurso",id);
        model.addAttribute("nombreCurso",c.getNombre());
        model.addAttribute("idTitulo",c.getTitulo().getId());
        model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
        model.addAttribute("alumno",new Alumno());
        return "jf/adminCursoAddAlumno";
    }

    @PostMapping("/adminAlumnosCurso/addAlumnoACurso/{id}")
    public String addAlumnoACurso(@PathVariable Long id, @ModelAttribute("alumno")Alumno alumno, Model model){
        profesorServicio.anyaidrAlumnoACurso(alumnoServicio, cursoServicio,alumno,id, usuarioServicio, envioEmailServicio);
        return accederAlumnos(id,  model);
    }

    //Modificar alumno a curso
    @GetMapping("/adminAlumnosCurso/editAlumnoACurso/{id}")
    public String modificarAlumnoDeCurso(@PathVariable Long id, Model model){
        Alumno a=alumnoServicio.findById(id);
        Curso c=a.getCurso();
        model.addAttribute("idCurso",a.getCurso().getId());
        model.addAttribute("nombreCurso",c.getNombre());
        model.addAttribute("idTitulo",c.getTitulo().getId());
        model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
        model.addAttribute("alumno",a);
        return "jf/adminCursoModificarAlumno";
    }

    @PostMapping("/adminAlumnosCurso/editAlumnoACurso/{id}")
    public String modificarAlumnoDeCurso(@ModelAttribute("alumno")Alumno alumno, @PathVariable Long id, Model model){
        Alumno a=alumnoServicio.findById(id);
        profesorServicio.editarAlumnoMenosCurso( a, alumno, alumnoServicio, cursoServicio);
        return accederAlumnos(a.getCurso().getId(),  model);
    }
    /*
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
    */
    @GetMapping("/horarioAsignatura/{id}")
    public String accederHorarioAsignatura(@PathVariable Long id, Model model){
         Asignatura a=asignaturaServicio.findById(id);
         Curso c=a.getCurso();
         model.addAttribute("nombreCurso",c.getNombre());
         model.addAttribute("idCurso",c.getId());
         model.addAttribute("idTitulo",c.getTitulo().getId());
         model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
         model.addAttribute("horarios",a.getHorarios());
         model.addAttribute("asignatura",a);
         return "jf/adminHorarioAsignatura";
    }

    @GetMapping("/horarioAsignaturaCompleto/{id}")
    public String accederHorarioAsignaturaCompleto(@PathVariable Long id, Model model){
        Asignatura a=asignaturaServicio.findById(id);
        Curso c=a.getCurso();
        model.addAttribute("nombreCurso",c.getNombre());
        model.addAttribute("idCurso",c.getId());
        model.addAttribute("idTitulo",c.getTitulo().getId());
        model.addAttribute("nombreTitulo",c.getTitulo().getNombre());
        model.addAttribute("horarios",a.getHorarios());
        model.addAttribute("asignatura",a);
        model.addAttribute("horario",asignaturaServicio.obtenerHorarioAsignatura(a));
        return "jf/adminHorarioAsignaturaCompleto";
    }

//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/accederConvalidaciones/{id}")
    public String accederConvalidacionesAlumno(@PathVariable Long id, Model model){
        model.addAttribute("convalidaciones", profesorServicio.obtenerConvalidacionesPendientes(alumnoServicio, id));
        model.addAttribute("alumno", alumnoServicio.findById(id));
        return "jf/adminAlumnoConvalidaciones";
    }

    @GetMapping("/negarConvalidacion/{id}")
    public String negarConvalidacion(@PathVariable Long id, Model model){
        Long idAlumno=situacionExcepcionalServicio.findById(id).getAlumno().getId();
        profesorServicio.negarConvalidacion(id, situacionExcepcionalServicio, alumnoServicio, asignaturaServicio);
        return accederConvalidacionesAlumno( idAlumno,  model);
    }

    @GetMapping("/aceptarConvalidacion/{id}")
    public String aceptarConvalidacion(@PathVariable Long id, Model model){
        profesorServicio.aceptarConvalidacion(id, situacionExcepcionalServicio, alumnoServicio,asignaturaServicio);
        return accederConvalidacionesAlumno( situacionExcepcionalServicio.findById(id).getAlumno().getId(),  model);
    }



}
