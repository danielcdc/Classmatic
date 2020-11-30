package com.salesianostriana.classmatic;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Asignatura;
import com.salesianostriana.classmatic.entidades.SituacionExcepcional;
import com.salesianostriana.classmatic.repositorios.AlumnoRepositorio;
import com.salesianostriana.classmatic.repositorios.AsignaturaRepositorio;
import com.salesianostriana.classmatic.repositorios.SituacionExcepcionalRepositorio;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.AsignaturaServicio;
import com.salesianostriana.classmatic.servicios.ProfesorServicio;
import com.salesianostriana.classmatic.servicios.SituacionExcepcionalServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class NegarConvalidacionTest {

    @Mock
    SituacionExcepcionalRepositorio repositorioSE;

    @Mock
    AlumnoRepositorio repositorioAL;

    @Mock
    AsignaturaRepositorio repositorioAS;

    @InjectMocks
    ProfesorServicio servicioPF;

    @Mock
    SituacionExcepcionalServicio servicioSIT;

    @Mock
    AlumnoServicio servicioAL;

    @Mock
    AsignaturaServicio servicioAS;


    /**
     * Comprobamos que el método responde adecuadamente cuando se le pasa los parámetros de los tipos correctos.
     * Este test tiene enfoque de caja negra.
     */
    @Test
    void negarConvalidadacionTest(){
        // Mock de ProfesorServicio e instancias de los servicios que este requiere.
        ProfesorServicio servicio = Mockito.mock(ProfesorServicio.class);
        SituacionExcepcionalServicio sit = new SituacionExcepcionalServicio(repositorioSE);
        AlumnoServicio al = new AlumnoServicio(repositorioAL);
        AsignaturaServicio as = new AsignaturaServicio(repositorioAS);
        // Elaboramos una respuesta, con las respuestas esperadas.
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            Object arg2 = invocation.getArgument(2);
            Object arg3 = invocation.getArgument(3);
            Assertions.assertEquals(1L, arg0);
            Assertions.assertEquals(sit, arg1);
            Assertions.assertEquals(al,arg2);
            Assertions.assertEquals(as, arg3);
            return null;
        }).when(servicio).negarConvalidacion(anyLong(), isA(SituacionExcepcionalServicio.class), isA(AlumnoServicio.class)
                , isA(AsignaturaServicio.class));
        // Le proporcionamos al método los parámetros que se espera, pasándolo si funciona adecuadamente.
        servicio.negarConvalidacion(1L,sit,al,as);
    }

    /**
     * Comprobamos que el método borra la instancia de "SituaciónExcepcional".
     */
    @Test
    void negarConvalidacionBorrado(){
        // Proporcionamos las instancias de las entidades implicadas.
        Alumno alumno = new Alumno();
        alumno.setSituacionesExc(new ArrayList<SituacionExcepcional>());
        Asignatura asignatura = new Asignatura();
        asignatura.setSituacionExc(new ArrayList<SituacionExcepcional>());
        SituacionExcepcional sitEx = new SituacionExcepcional(1L, LocalDate.of(2020,9,20),
                LocalDate.of(2020, 10,12 ), true, "",
                alumno, new Asignatura());

        // Relacionamos las entidades, añadiendo a Alumno y a Asignatura la referencia a la misma entidad de
        // situación excepcional
        alumno.getSituacionesExc().add(sitEx);
        asignatura.getSituacionExc().add(sitEx);
        sitEx.setAlumno(alumno);
        sitEx.setAsignatura(asignatura);

        // Usando ServicioProfesor, llamamos al método a testear.
        when(servicioSIT.findById(1L)).thenReturn(sitEx);
        when(servicioAL.edit(alumno)).thenReturn(alumno);
        when(servicioAS.edit(asignatura)).thenReturn(asignatura);
        servicioPF.negarConvalidacion(1L, servicioSIT, servicioAL, servicioAS);

        // Comprobamos que la relación entre las entidades ya no existen y que la situación excepcional ha sido borrada.
        Assertions.assertTrue(!alumno.getSituacionesExc().contains(sitEx));
        Assertions.assertTrue(!asignatura.getSituacionExc().contains(sitEx));
    }
}
