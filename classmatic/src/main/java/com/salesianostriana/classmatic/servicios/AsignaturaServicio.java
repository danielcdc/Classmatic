package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Asignatura;
import com.salesianostriana.classmatic.entidades.Horario;
import com.salesianostriana.classmatic.repositorios.AsignaturaRepositorio;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class AsignaturaServicio extends ServicioBaseImp<Asignatura,Long, AsignaturaRepositorio> {

    private final AsignaturaRepositorio asignaturaRepositorio;

    /**
     * Primero, imprime el horario con todo a libre.
     * Segundo, crea un arraylist con todos los horarios que tiene la asignatura.
     * Tercero, comparamos los elementos de ambos arrays, y de coincidir el día y la hora, se
     * cambia el valor del array inicial (horario) de "libre" a el nombre de la asignatura en cuestión.
     *
     * @param a La asignatura que se pretende insertar en el horario
     * @return El horario correspondiente a una asignatura.
     */
    public List<List<String>> obtenerHorarioAsignatura(Asignatura a){
        List<List<String>>horario=new ArrayList<List<String>>();
        for(int i=0;i<5;i++){
            horario.add(new ArrayList<String>());
            for(int j=0;j<6;j++){
                horario.get(i).add("Libre");
            }
        }
        for(int i=0;i<horario.size();i++){ // Recorre los días de Lunes a Viernes
            // Añadir un try catch para captar NullPointerException
            for(int j=0;j<horario.get(i).size();j++){ // Recorre las horas de 8 a 2
                for(Horario h : a.getHorarios()){ // Comprueba si el objeto horario coincide con algún día y hora.
                    if(h.getDia()==(i+1)){ // Comprueba si la asignatura se imparte en un día de la semana.
                        for(Integer hora : h.getHoras()){
                            if(hora==(j+1)){ // Comprueba si la asignatura se imparte en una o varias horas de un día de la semana.
                                horario.get(i).set(j,a.getNombre());
                            }
                        }
                    }
                }
            }
        }
        return horario;
    }

}
