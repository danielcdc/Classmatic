package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.*;
import com.salesianostriana.classmatic.repositorios.ProfesorRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ProfesorServicio extends ServicioBaseImp<Profesor,Long, ProfesorRepositorio> {

    private final ProfesorRepositorio profesorReporitorio;


    public void anyadirProfesor(Profesor p, Profesor pro, UsuarioServicio usuarioServicio,
                                EnvioEmailServicio envioEmailServicio){
        p.setNombre(pro.getNombre());
        p.setApellidos(pro.getApellidos());
        p.setEmail(pro.getEmail());
        p.setEsJefe(pro.isEsJefe());
        p.setFechaNacimiento(pro.getFechaNacimiento());
        p.setCodigoInvitacion(usuarioServicio.autogenerarCodigo());
        save(p);
        String mensaje="Hola "+p.getNombre()+" "+p.getApellidos()+".\nSu cuenta está creada, solo requiere de su activación." +
                " Acceda a localhost:9000/invitacion, introduzca la clave y su contraseña deseada.\n" +
                "CLAVE: "+ p.getCodigoInvitacion();
        envioEmailServicio.sendEmail(p,"Valida tu cuenta",mensaje);
    }

    public void anyadirAlumno(Alumno alumnoForm, AlumnoServicio alumnoServicio,
                              CursoServicio cursoServicio, UsuarioServicio usuarioServicio,
                              EnvioEmailServicio envioEmailServicio){
        Alumno a=new Alumno();
        Curso c=cursoServicio.findById(alumnoForm.getCurso().getId());
        a.setNombre(alumnoForm.getNombre());
        a.setApellidos(alumnoForm.getApellidos());
        a.setFechaNacimiento(alumnoForm.getFechaNacimiento());
        a.setCurso(c);
        a.setEmail(alumnoForm.getEmail());
        a.setAsignaturas(new ArrayList<Asignatura>());
        a.setSolicitudesAmp(new ArrayList<SolicitudAmpliacionMatricula>());
        a.setSituacionesExc(new ArrayList<SituacionExcepcional>());
        a.setCodigoInvitacion(usuarioServicio.autogenerarCodigo());
        c.addAlumno(a);
        alumnoServicio.edit(a);
        cursoServicio.edit(c);String mensaje="Hola "+a.getNombre()+" "+a.getApellidos()+".\nSu cuenta está creada, solo requiere de su activación." +
                " Acceda a localhost:9000/invitacion, introduzca la clave y su contraseña deseada.\n" +
                "CLAVE: "+ a.getCodigoInvitacion();
        envioEmailServicio.sendEmail(a,"Valida tu cuenta",mensaje);
    }

    public void anyaidrAlumnoACurso(AlumnoServicio alumnoServicio, CursoServicio cursoServicio,
                                    Alumno al, Long id, UsuarioServicio usuarioServicio,
                                    EnvioEmailServicio envioEmailServicio){
        Alumno a=new Alumno();
        Curso c=cursoServicio.findById(id);
        a.setNombre(al.getNombre());
        a.setApellidos(al.getApellidos());
        a.setFechaNacimiento(al.getFechaNacimiento());
        a.setCurso(c);
        a.setEmail(al.getEmail());
        a.setAsignaturas(new ArrayList<Asignatura>());
        a.setSolicitudesAmp(new ArrayList<SolicitudAmpliacionMatricula>());
        a.setSituacionesExc(new ArrayList<SituacionExcepcional>());
        a.setCodigoInvitacion(usuarioServicio.autogenerarCodigo());
        alumnoServicio.edit(a);
        cursoServicio.edit(c);
        String mensaje="Hola "+a.getNombre()+" "+a.getApellidos()+".\nSu cuenta está creada, solo requiere de su activación." +
                " Acceda a localhost:9000/invitacion, introduzca la clave y su contraseña deseada.\n" +
                "CLAVE: "+ a.getCodigoInvitacion();
        envioEmailServicio.sendEmail(a,"Valida tu cuenta",mensaje);
    }

    public void editarAlumno(Alumno a, Alumno al, AlumnoServicio alumnoServicio, CursoServicio cursoServicio) {
        a.setNombre(al.getNombre());
        a.setApellidos(al.getApellidos());
        a.setEmail(al.getEmail());
        a.setFechaNacimiento(al.getFechaNacimiento());
        Curso cAnt=a.getCurso();
        Curso cNew=cursoServicio.findById(al.getCurso().getId());
        if(!cAnt.equals(cNew)){
            cAnt.removeAlumno(a);
            cNew.addAlumno(a);
            cursoServicio.edit(cAnt);
            cursoServicio.edit(cNew);
        }
        alumnoServicio.edit(a);
    }

    public void editarAlumnoMenosCurso(Alumno a, Alumno al, AlumnoServicio alumnoServicio, CursoServicio cursoServicio) {
        a.setNombre(al.getNombre());
        a.setApellidos(al.getApellidos());
        a.setEmail(al.getEmail());
        a.setFechaNacimiento(al.getFechaNacimiento());
        alumnoServicio.edit(a);
    }


    public void editarProfesor(Profesor p, Profesor pr) {
        p.setNombre(pr.getNombre());
        p.setApellidos(pr.getApellidos());
        p.setEmail(pr.getEmail());
        p.setFechaNacimiento(pr.getFechaNacimiento());
        p.setEsJefe(pr.isEsJefe());
        edit(p);

    }

    public void editarTitulo(Titulo t, Titulo tit, TituloServicio tituloServicio){
        t.setNombre(tit.getNombre());
        t.setNivelAcademico(tit.getNivelAcademico());
        tituloServicio.edit(t);
    }

    public void borrarTitulo(TituloServicio tituloServicio, CursoServicio cursoServicio,
                             AlumnoServicio alumnoServicio, AsignaturaServicio asignaturaServicio,
                             Long id){

        List<Alumno> listaAl=new ArrayList<Alumno>();
        List<Asignatura> listaAs=new ArrayList<Asignatura>();
        List<Curso>listaCu=new ArrayList<Curso>();
        Titulo t=tituloServicio.findById(id);
       //desvincular titulo y sus cursos
        for(int j=0;j<t.getCursos().size();j++){

                //desvinculo curso y sus asignaturas
                if(!t.getCursos().get(j).getAsignaturas().isEmpty()){
                    for(int i=0;i<t.getCursos().get(j).getAsignaturas().size();i++){


                        listaAs.add(t.getCursos().get(j).getAsignaturas().get(i));
                    }
                    for(Asignatura as : listaAs){
                        t.getCursos().get(j).removeAsignatura(as);
                        asignaturaServicio.edit(as);
                    }
                }
                if(!t.getCursos().get(j).getAlumnos().isEmpty()){
                    for(int i=0;i<t.getCursos().get(j).getAlumnos().size();i++){


                        listaAl.add(t.getCursos().get(j).getAlumnos().get(i));

                    }
                    for(Alumno al : listaAl){
                        t.getCursos().get(j).removeAlumno(al);
                        alumnoServicio.edit(al);
                    }
                }
                listaCu.add(t.getCursos().get(j));
        }
        for(Curso cu : listaCu){
            t.removeCurso(cu);
            cursoServicio.edit(cu);
            cursoServicio.delete(cu);
        }
        tituloServicio.delete(t);
    }

    public void anyadirCurso(CursoServicio cursoServicio, TituloServicio tituloServicio, Curso curso, Long id){
        cursoServicio.save(curso);
        tituloServicio.findById(id).addCurso(curso);
        cursoServicio.edit(curso);
    }

    public void editarCurso(Curso c, Curso cur, CursoServicio cursoServicio){
        c.setNombre(cur.getNombre());
        cursoServicio.edit(c);
    }

    public Long eliminarCurso(Curso c,AsignaturaServicio asignaturaServicio,
                              AlumnoServicio alumnoServicio, CursoServicio cursoServicio,
                              TituloServicio tituloServicio){
        List<Asignatura> listaAs=new ArrayList<Asignatura>();
        List<Alumno> listaAl=new ArrayList<Alumno>();
        Long id=c.getTitulo().getId();
        //List<Asignatura> listaAs=c.getAsignaturas();
        //List<Alumno> listaAl=c.getAlumnos();
        listaAs.addAll(c.getAsignaturas());
        listaAl.addAll(c.getAlumnos());
        Titulo tit= c.getTitulo();

        for(Asignatura as : listaAs)/*for(int i=0;i<c.getAsignaturas().size();i++)*/{
            c.removeAsignatura(as);
            asignaturaServicio.edit(as);
        }
        for(Alumno al : listaAl){
            c.removeAlumno(al);
            alumnoServicio.edit(al);
        }
        //Long id=c.getTitulo().getId();
        tit.removeCurso(c);
        tituloServicio.edit(tit);
        cursoServicio.delete(c);

        return id;
    }

    public Long eliminarAsignatura(Long id, AsignaturaServicio asignaturaServicio,
                                   CursoServicio cursoServicio,
                                   AlumnoServicio alumnoServicio,
                                   HorarioServicio horarioServicio){
        Asignatura as=asignaturaServicio.findById(id);
        List<Alumno>listaAl=new ArrayList<Alumno>();
        List<Horario>listaHorarios=new ArrayList<Horario>();
        listaHorarios.addAll(as.getHorarios());
        listaAl.addAll(as.getAlumnos());
        for(Alumno a: listaAl){
            a.removeAsignatura(as);
            alumnoServicio.edit(a);
        }
        for(Horario hor: listaHorarios){
            hor.removeAsignatura(as);
            horarioServicio.delete(hor);
        }
        Curso c=as.getCurso();
        c.removeAsignatura(as);
        cursoServicio.edit(c);
        asignaturaServicio.delete(as);
        return c.getId();
    }

    public void crearAsignatura(Long id, Asignatura as,
                                AsignaturaServicio asignaturaServicio,
                                CursoServicio cursoServicio){
        Curso c=cursoServicio.findById(id);
        asignaturaServicio.save(as);
        c.addAsignatura(as);
        asignaturaServicio.edit(as);
        cursoServicio.edit(c);
    }

    public void editarAsignatura(Long id,
                                 AsignaturaServicio asignaturaServicio,
                                 Asignatura asignatura){
        Asignatura as=asignaturaServicio.findById(id);
        as.setNombre(asignatura.getNombre());
        as.setNHorasSemanales(asignatura.getNHorasSemanales());
        asignaturaServicio.edit(as);
    }

    //Habra que modificarlo cuando se metan solicitudes de situacionesExcepcionales
    public void eliminarAlumno(AlumnoServicio alumnoServicio,
                               AsignaturaServicio asignaturaServicio,
                               CursoServicio cursoServicio,
                               Long id){
        Alumno al=alumnoServicio.findById(id);
        List<Asignatura>listaAs=new ArrayList<Asignatura>();
        Curso c=al.getCurso();
        c.removeAlumno(al);
        cursoServicio.edit(c);
        for(Asignatura as : listaAs){
            al.removeAsignatura(as);
            asignaturaServicio.edit(as);
        }
        alumnoServicio.delete(al);
    }

    /**
     * @deprecated Este método está definido en AlumnoServicio
     */
    public void crearHorarioAlumno(Alumno al){
        List<Horario>listaHorarios = new ArrayList<Horario>();


        for(Asignatura as: al.getAsignaturas()){
            for(Horario h : as.getHorarios()){
                listaHorarios.add(h);
            }
        }


        List<List<Asignatura>>listaCompleta= new ArrayList();
        for(int i=0;i<5;i++){
            listaCompleta.add(new ArrayList<Asignatura>());
        }
        for(List l : listaCompleta){//relleno las 6 h de lo 5 d con falso
            for(int i=0;i<6;i++){
                l.add(false);
            }
        }

        for(int i=0;i<5;i++){
            for(int j=0;j<6;j++){
                for(Horario h : listaHorarios){
                    for(int hora : h.getHoras()){
                        if(h.getDia()==(i+1)&&hora==(j+1)){
                            listaCompleta.get(i).set(j,h.getAsignatura());
                        }
                    }
                }
            }
        }
    }


    /*
    Tests:
    -Alumno sin convalidaciones pendientes
    -Alumno con  alguna convalidacion nula
    -Alumno nulo
    -Alumno inexistente
    Hace falta un Mock alumnoServicio
     */
    public List<SituacionExcepcional> obtenerConvalidacionesPendientes(AlumnoServicio alumnoServicio, Long id){
        List<SituacionExcepcional> convalidacionesCompleta = new ArrayList<SituacionExcepcional>();
        if(alumnoServicio.findById(id) != null) {
            convalidacionesCompleta.addAll(alumnoServicio.findById(id).getSituacionesExc());
            List<SituacionExcepcional> convalidacionesPendientes = new ArrayList<SituacionExcepcional>();
            convalidacionesPendientes = convalidacionesCompleta.stream().filter((x) -> x.isResuelta() == false).collect(Collectors.toList());
            return convalidacionesPendientes;
        }else{
            return convalidacionesCompleta;
        }

    }

    /**
     * Encuentra la situación excepcional mediante su id, eliminando las relaciones/asociaciones con alumno y asignatura
     * para posteriormente borrar el objeto "situación excepcional".
     * @param id El id del objeto situación excepcional.
     * @param situacionExcepcionalServicio Instancia del servicio situación excepcional.
     * @param alumnoServicio Instancia del servicio Alumno.
     * @param asignaturaServicio Instancia del servicio Asignatura.
     */
    public void negarConvalidacion(Long id, SituacionExcepcionalServicio situacionExcepcionalServicio,
                                   AlumnoServicio alumnoServicio, AsignaturaServicio asignaturaServicio){
        // Encuentra mediante el id el objeto situaciónExcepcional
        SituacionExcepcional sit = situacionExcepcionalServicio.findById(id);
        Alumno al = sit.getAlumno();
        Asignatura as = sit.getAsignatura();
        // Elimina las relaciones con alumno y y asignatura
        al.removeSituacionExcepcional(sit);
        as.removeSituacionExcepcional(sit);
        // Acualiza los campos de "situación excepcional" de las entidades alumno y asingatura implicados.
        alumnoServicio.edit(al);
        asignaturaServicio.edit(as);
        // Elimina la instancia de situaciónExcepcional.
        situacionExcepcionalServicio.delete(sit);
    }

    /*
    Pediente de revisión.
     */
    public void aceptarConvalidacion(Long id, SituacionExcepcionalServicio situacionExcepcionalServicio,
                                     AlumnoServicio alumnoServicio, AsignaturaServicio asignaturaServicio){
        SituacionExcepcional sit = situacionExcepcionalServicio.findById(id);
        sit.setResuelta(true);
        sit.setFechaResolucion(LocalDate.now());
        Alumno al=sit.getAlumno();
        Asignatura as=sit.getAsignatura();
        al.removeAsignatura(as);//No desvincula la asignatura
        System.out.println("Comprobacion 1");//Aqui ya se han multiplicado las asignaturas de alumno
        for(Asignatura asign: al.getAsignaturas()){//comprobaciones
            System.out.println(asign.getNombre());
        }
        alumnoServicio.edit(al);
        System.out.println("Comprobacion 2");
        for(Asignatura asign: al.getAsignaturas()){//comprobaciones
            System.out.println(asign.getNombre());
        }
        asignaturaServicio.edit(as);
        System.out.println("Comprobacion 3");
        for(Asignatura asign: al.getAsignaturas()){//comprobaciones
            System.out.println(asign.getNombre());
        }
        sit.getAlumno().removeAsignatura(sit.getAsignatura());
        System.out.println("Comprobacion 4");
        for(Asignatura asign: al.getAsignaturas()){//comprobaciones
            System.out.println(asign.getNombre());
        }
        //alumnoServicio.edit(sit.getAlumno());
        //asignaturaServicio.edit(sit.getAsignatura());
        situacionExcepcionalServicio.edit(sit);
        System.out.println("Comprobacion 5");
        for(Asignatura asign: al.getAsignaturas()){//comprobaciones
            System.out.println(asign.getNombre());
        }
    }


}




