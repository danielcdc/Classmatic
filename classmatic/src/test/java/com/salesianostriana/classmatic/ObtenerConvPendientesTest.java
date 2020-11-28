package com.salesianostriana.classmatic;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.SituacionExcepcional;
import com.salesianostriana.classmatic.repositorios.ProfesorRepositorio;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.ProfesorServicio;
import org.junit.jupiter.api.*;
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
public class ObtenerConvPendientesTest {

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

    @BeforeEach
    public void preparar(){
        alumnoPrueba.getSituacionesExc().clear();
    }

    /*
    En este test se replica el funcionamiento del método en circunstancias completamente favorables
    con un Alumno que tiene una SituacionExcepcional estando los dos correctos.
     */
    @Disabled
    @Test
    @DisplayName("Con convalidacion valida")
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

    /*
    En este test se prueba con un alumno que no tiene ninguna SituacionExcepcioan pendiente
     */
    @Disabled
    @Test
    @DisplayName("Con convalidacion vacia")
    public void obtenerConvalidacionConUnaConvalidacionVaciaTest(){
        SituacionExcepcional convalidacion = new SituacionExcepcional();
        //alumnoPrueba.getSituacionesExc().add(convalidacion);
        //convalidacion.setAlumno(alumnoPrueba);
        alumnoPrueba.addSituacionExcepcional(convalidacion);
        List<SituacionExcepcional> lista = new ArrayList<SituacionExcepcional>();
        lista.add(convalidacion);
        Mockito.when(alumnoServicio.findById(alumnoPrueba.getId())).thenReturn(alumnoPrueba);
        assertEquals(profesorServicio.obtenerConvalidacionesPendientes(alumnoServicio, alumnoPrueba.getId()),lista);
    }

    /*
    En este método se prueba que no exista ningun alumno con el Alumno.id que se le pasa al método
     */
    @Disabled
    @Test
    @DisplayName("Alumno no encontrado")
    public void obtenerConvalidacionAlumnoNoEncontradoTest(){
        SituacionExcepcional convalidacion = new SituacionExcepcional();
        //alumnoPrueba.getSituacionesExc().add(convalidacion);
        //convalidacion.setAlumno(alumnoPrueba);
        //alumnoPrueba.addSituacionExcepcional(convalidacion);
        List<SituacionExcepcional> lista = new ArrayList<SituacionExcepcional>();
        //lista.add(convalidacion);
        Mockito.when(alumnoServicio.findById(alumnoPrueba.getId())).thenReturn(alumnoPrueba);
        assertEquals(profesorServicio.obtenerConvalidacionesPendientes(alumnoServicio, alumnoPrueba.getId()),lista);
    }



}
