package com.salesianostriana.classmatic.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Asignatura;
import com.salesianostriana.classmatic.entidades.SituacionExcepcional;
import com.salesianostriana.classmatic.ficheros.StorageService;
import com.salesianostriana.classmatic.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alumno/")
public class AlumnoController {

    @Autowired
    AlumnoServicio alumnoServicio;

    @Autowired
    HorarioServicio horarioServicio;

    @Autowired
    AsignaturaServicio asignaturaServicio;

    @Autowired
    CursoServicio cursoServicio;

    @Autowired
    StorageService storageService;

    @Autowired
    SituacionExcepcionalServicio situacionExcepcionalServicio;

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
    public String accederConvalidaciones(@AuthenticationPrincipal Alumno alumno, Model model){
        List<Asignatura>asignaturas;
        asignaturas=alumnoServicio.sacarAsignaturas(alumno, asignaturaServicio, cursoServicio);
        model.addAttribute("asignaturas",asignaturas);
        return "alumno/alumnoConvalidaciones";
    }


    @GetMapping("/convalidarAsignatura/{id}")
    public String convalidarAsignatura(@PathVariable Long id, Model model){
        model.addAttribute("asignatura", asignaturaServicio.findById(id));
        model.addAttribute("situacionExcepcional",new SituacionExcepcional());
        return "alumno/convalidarAsignatura";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }

    @PostMapping("/convalidarAsignatura/{id}")
    public String enviarConvalidacion(@ModelAttribute("situacionExcepcional")SituacionExcepcional situacionExcepcional,
                                      @PathVariable Long id, Model model,
                                      @RequestParam("archivo")MultipartFile archivo,
                                      BindingResult bindingResult, @AuthenticationPrincipal Alumno alumno){
        System.out.println(situacionExcepcional.getId()+"----------------------------------------------------------");
        if(!archivo.isEmpty()){
            String ruta = storageService.store(archivo, situacionExcepcional.getId());
            situacionExcepcional.setArchivoConvalidacion(MvcUriComponentsBuilder
                .fromMethodName(AlumnoController.class, "serveFile", ruta).build().toUriString());
            alumnoServicio.convalidar(asignaturaServicio, situacionExcepcionalServicio, id, situacionExcepcional,
                    alumno, ruta );
            return accederConvalidaciones( alumno, model);
        }else{
            return convalidarAsignatura( id,  model);
        }

    }




    @GetMapping("/ampliaciones")
    public String accederAmpliacion(){
        return "alumno/alumnoAmpliaciones";
    }
}
