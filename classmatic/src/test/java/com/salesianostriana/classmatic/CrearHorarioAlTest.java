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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    -Alumno con asignatura con horario fuera del horario, es decir, dia superior a 5, inferior a 1
    y hora superior a 6 o inferior a 1
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
        alumnoPrueba.getAsignaturas().clear();
        asignaturaPrueba.getAlumnos().clear();
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
    todo a false excepto las horas de esa asignatura que deberán contener dicha asignatura
     */
    @Disabled
    @Test
    @DisplayName("Alumno con una asignatura")
    public void crearHorarioConUnaAsignatura(){

        /*
        Primero  asocio la asignatura y el alumno, posteriormente creo los horarios y las asocio con asignatura,
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

    /*
    En este test se va a probar con un alumno que tiene una asignatura que no tiene ningun horario,
    debería obtenerse una lista de listas con estas últimas con todo a false
     */
    @Disabled
    @DisplayName("Asignatura sin horarios")
    @Test
    public void crearHorarioSinHorarios(){
        alumnoPrueba.getAsignaturas().add(asignaturaPrueba);
        asignaturaPrueba.getAlumnos().add(alumnoPrueba);
        for (Horario h : asignaturaPrueba.getHorarios()) {
            Mockito.when(horarioServicio.obtenerHoras(h)).thenReturn(h.getHoras());
        }

        //Aqui creo una lista de listas(cada una un día, y cada elemento una hora) con todo a false
        List<List<Asignatura>>listaCompleta= new ArrayList();
        for(int i=0;i<5;i++){
            listaCompleta.add(new ArrayList<Asignatura>());
        }
        for(List l : listaCompleta){//relleno las 6 h de lo 5 d con falso
            for(int i=0;i<6;i++){
                l.add(false);
            }
        }

        List <List<Asignatura>> horario = alumnoServicio.crearHorarioAlumno(alumnoPrueba, horarioServicio);
        assertEquals(listaCompleta, horario);
    }

    /*
    Aqui vamos a probar con una asignatura que tiene dos horarios que coinciden de dia y hora,
    debería sobreescribir el que itere último sobre el primero, resultando como si no existiera el horario repetido
     */
    @Disabled
    @DisplayName("Asignatura con horarios repetidos")
    @Test
    public void crearHorarioConHorariosRepetidos(){
        alumnoPrueba.getAsignaturas().add(asignaturaPrueba);
        asignaturaPrueba.getAlumnos().add(alumnoPrueba);
        Horario hor1 = new Horario();
        Horario hor2 = new Horario();
        hor1.setId(14532456L);
        hor1.setDia(1);
        hor1.setHoras(new ArrayList<>());
        hor1.getHoras().add(1);
        hor2.setId(14592456L);
        hor2.setDia(1);
        hor2.setHoras(new ArrayList<>());
        hor2.getHoras().add(1);
        hor1.addAsignatura(asignaturaPrueba);
        hor2.addAsignatura(asignaturaPrueba);
        for (Horario h : asignaturaPrueba.getHorarios()) {
            Mockito.when(horarioServicio.obtenerHoras(h)).thenReturn(h.getHoras());
        }
        List <List<Asignatura>> horario = alumnoServicio.crearHorarioAlumno(alumnoPrueba, horarioServicio);
        for(int i = 0; i< horario.size(); i++){
            for(int j = 0; j < horario.get(i).size(); j++){
                if((i+1)==hor1.getDia() && hor1.getHoras().contains((j+1))){
                    assertEquals(horario.get(i).get(j),asignaturaPrueba);
                }else{
                    assertEquals(horario.get(i).get(j),false);
                }
            }
        }
    }

    /*
    Vamos a testear un alumno con una asignatura que tiene distintos horarios que se salen del mismo,
    debería devolver una lista de listas con todo a false
     */
    //@Disabled
    @DisplayName("Horarios fuera de horario lectivo")
    @Test
    public void crearHorarioConHorariosErroneos(){
        alumnoPrueba.getAsignaturas().add(asignaturaPrueba);
        asignaturaPrueba.getAlumnos().add(alumnoPrueba);
        Horario hor1 = new Horario();
        Horario hor2 = new Horario();
        Horario hor3 = new Horario();
        Horario hor4 = new Horario();
        hor1.setId(14532456L);
        hor1.setDia(-1);
        hor1.setHoras(new ArrayList<>());
        hor1.getHoras().add(1);
        hor2.setId(14592456L);
        hor2.setDia(8);
        hor2.setHoras(new ArrayList<>());
        hor2.getHoras().add(5);
        hor3.setDia(1);
        hor3.setHoras(new ArrayList<>());
        hor3.getHoras().add(-1);
        hor4.setDia(1);
        hor4.setHoras(new ArrayList<>());
        hor4.getHoras().add(8);
        hor1.addAsignatura(asignaturaPrueba);
        hor2.addAsignatura(asignaturaPrueba);
        for (Horario h : asignaturaPrueba.getHorarios()) {
            Mockito.when(horarioServicio.obtenerHoras(h)).thenReturn(h.getHoras());
        }
        List <List<Asignatura>> horario = alumnoServicio.crearHorarioAlumno(alumnoPrueba, horarioServicio);
        for(int i = 0; i< horario.size(); i++){
            for(int j = 0; j < horario.get(i).size(); j++){
                assertEquals(horario.get(i).get(j),false);
            }
        }
    }
}
