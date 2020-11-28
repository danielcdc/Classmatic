package com.salesianostriana.classmatic;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.SituacionExcepcional;
import com.salesianostriana.classmatic.repositorios.ProfesorRepositorio;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.ProfesorServicio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CarlosTest {

    //@Mock
    AlumnoServicio alumnoServicio = Mockito.mock(AlumnoServicio.class);

    @Mock
    static ProfesorRepositorio profesorRepositorio;

    @InjectMocks
    static ProfesorServicio profesorServicio;

    static Alumno alumnoPrueba;


    @BeforeAll
    public static void inicializando(){
        alumnoPrueba = new Alumno();
        alumnoPrueba.setSituacionesExc(new ArrayList<SituacionExcepcional>());
        alumnoPrueba.setId(12345678);
    }

    @Test
    public void obtenerConvalidacionConUnaConvalidacionTest(){
        SituacionExcepcional convalidacion = new SituacionExcepcional();
        convalidacion.setResuelta(false);
        convalidacion.setFechaSolicitud(LocalDate.now());
        alumnoPrueba.getSituacionesExc().add(convalidacion);
        convalidacion.setAlumno(alumnoPrueba);
        List<SituacionExcepcional> lista = new ArrayList<SituacionExcepcional>();
        lista.add(convalidacion);
        Mockito.when(alumnoServicio.findById(alumnoPrueba.getId())).thenReturn(alumnoPrueba);
        assertEquals(profesorServicio.obtenerConvalidacionesPendientes(alumnoServicio, alumnoPrueba.getId()),lista);
    }

    /*@Test
    public void prueba(){
        List<Alumno> listaAl = new ArrayList<Alumno>();
        Mockito.when(alumnoServicio.findById(alumnoPrueba.getId())).thenReturn(alumnoPrueba);
        alumnoServicio.findById(alumnoPrueba.getId());
        assertTrue(true);
    }*/

    /*
    ProfesorServicio.obtenerConvalidacionesPendientes() Tests:
    -Alumno sin convalidaciones pendientes
    -Alumno con  alguna convalidacion nula
    -Alumno nulo
    -Alumno inexistente
    Hace falta un Mock alumnoServicio
     */


}
