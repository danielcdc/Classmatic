package com.salesianostriana.classmatic;


import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Asignatura;
import com.salesianostriana.classmatic.entidades.Horario;
import com.salesianostriana.classmatic.repositorios.AlumnoRepositorio;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.HorarioServicio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CrearHorarioAlTest {

    /*
    Test previstos:
    -Alumno sin asignaturas, devuelve horario con todo a false
    -Alumno con asignatura, tendrá las horas de esa asignatura con esa asignatura incrustada, el resto de horas
    a false
    -Alumno con asignatura con dos horarios coincidentes, es decir que la asignatura tiene dos horarios con la misma
    hora y dia
    -Alumno con asignatura sin horarios
     */

    @Mock
    AlumnoRepositorio alumnoRepositorio;

    @Mock
    HorarioServicio horarioServicio;

    @InjectMocks
    AlumnoServicio alumnoServicio;

    static Alumno alumnoPrueba;
    static Asignatura asignaturaPrueba;

    @BeforeAll
    public static void prepararObjetos(){
        alumnoPrueba = new Alumno();
        alumnoPrueba.setId(12345678);
        asignaturaPrueba =  new Asignatura();
        asignaturaPrueba.setId(23456789);
        asignaturaPrueba.setHorarios(new ArrayList<Horario>());
        alumnoPrueba.setAsignaturas(new ArrayList<Asignatura>());
        //alumnoPrueba.getAsignaturas().add(asignaturaPrueba);
        //asignaturaPrueba.getHorarios();
    }

    /*
    En este test se prueba un alumno sin asignatura, el resutado debería ser que el horario tenga todos los
    huecos con false
     */
    @Test
    @DisplayName("Alumno sin asignaturas")
    public void crearHorarioSinAsignaturas(){
        for (Horario h : asignaturaPrueba.getHorarios()) {
            Mockito.when(horarioServicio.obtenerHoras(h)).thenReturn(h.getHoras());
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
        assertEquals(alumnoServicio.crearHorarioAlumno(alumnoPrueba, horarioServicio),listaCompleta);
    }






}
