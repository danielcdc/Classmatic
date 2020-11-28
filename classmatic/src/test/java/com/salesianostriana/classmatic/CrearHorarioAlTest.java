package com.salesianostriana.classmatic;


import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Asignatura;
import com.salesianostriana.classmatic.entidades.Horario;
import com.salesianostriana.classmatic.repositorios.AlumnoRepositorio;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.HorarioServicio;
import org.junit.jupiter.api.*;
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
    static List<List<Asignatura>>listaCompleta;

    @BeforeAll
    public static void prepararObjetos(){
        alumnoPrueba = new Alumno();
        alumnoPrueba.setId(12345678L);
        alumnoPrueba.setAsignaturas(new ArrayList<Asignatura>());
        asignaturaPrueba =  new Asignatura();
        asignaturaPrueba.setId(23456789L);
        asignaturaPrueba.setHorarios(new ArrayList<Horario>());
        asignaturaPrueba.setAlumnos(new ArrayList<Alumno>());
        alumnoPrueba.setAsignaturas(new ArrayList<Asignatura>());
        //alumnoPrueba.getAsignaturas().add(asignaturaPrueba);
        //asignaturaPrueba.getHorarios();
        listaCompleta = new ArrayList<>();
    }

    @BeforeEach
    public void restaurar(){
        listaCompleta.clear();
        for(int i=0;i<5;i++){
            listaCompleta.add(new ArrayList<Asignatura>());
        }
        for(List l : listaCompleta){//relleno las 6 h de lo 5 d con falso
            for(int i=0;i<6;i++){
                l.add(false);
            }
        }
        asignaturaPrueba.getHorarios().clear();
    }

    /*
    En este test se prueba un alumno sin asignatura, el resutado debería ser que el horario tenga todos los
    huecos con false
     */
    @Disabled
    @Test
    @DisplayName("Alumno sin asignaturas")
    public void crearHorarioSinAsignaturas(){
        for (Horario h : asignaturaPrueba.getHorarios()) {
            Mockito.when(horarioServicio.obtenerHoras(h)).thenReturn(h.getHoras());
        }
        assertEquals(alumnoServicio.crearHorarioAlumno(alumnoPrueba, horarioServicio),listaCompleta);
    }

    /*
    En este test el alumno tendrá una asignatura con un par de horarios, debería devolver el horario con
    todo a false excepto loas horas de esa asignatura que deberán contener dicha asignatura
     */
    @Disabled
    @Test
    @DisplayName("Alumno con una asignatura")
    public void crearHorarioConUnaAsignatura(){

        /*
        Primero  asocio la asignatura y el alumno, posteriormente creo los horarios y lso asocio con asignatura,
        las asociaciones se realizan setteando directamente para no intentar acceder a metodos del repositorio,
        se almacena el resultado del metodo a testear en un ArrayList<ArrayList<Asignatura>>(),
        y se comprueba recorriendo el resultado que  que todas las casillas almacenan false, excepto las que tienen
        un horario de la asignatura que almacenan dicha asignatura
         */
        alumnoPrueba.getAsignaturas().add(asignaturaPrueba);
        asignaturaPrueba.getAlumnos().add(alumnoPrueba);
        Horario hor1 = new Horario();
        Horario hor2 = new Horario();
        hor1.setId(14532456L);
        hor1.setDia(1);
        hor1.setHoras(new ArrayList<>());
        hor1.getHoras().add(1);
        hor2.setId(14592456L);
        hor2.setDia(3);
        hor2.setHoras(new ArrayList<>());
        hor2.getHoras().add(5);
        hor1.addAsignatura(asignaturaPrueba);
        hor2.addAsignatura(asignaturaPrueba);
        for (Horario h : asignaturaPrueba.getHorarios()) {
            Mockito.when(horarioServicio.obtenerHoras(h)).thenReturn(h.getHoras());
        }
        List <List<Asignatura>> horario = alumnoServicio.crearHorarioAlumno(alumnoPrueba, horarioServicio);
        for(int i = 0; i< horario.size(); i++){
            for(int j = 0; j < horario.get(i).size(); j++){
                if((i+1)==hor1.getDia() && hor1.getHoras().contains((j+1)) ||
                        (i+1)==hor2.getDia() && hor2.getHoras().contains((j+1))){
                    assertEquals(horario.get(i).get(j),asignaturaPrueba);
                }else{
                    assertEquals(horario.get(i).get(j),false);
                }
            }
        }
    }







}
