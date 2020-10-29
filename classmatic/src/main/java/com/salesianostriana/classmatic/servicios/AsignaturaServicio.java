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


    public List<List<String>> obtenerHorarioAsignatura(Asignatura a){
        List<List<String>>horario=new ArrayList<List<String>>();
        for(int i=0;i<5;i++){
            horario.add(new ArrayList<String>());
            for(int j=0;j<6;j++){
                horario.get(i).add("Libre");
            }
        }
        List<Horario>listaHorarios=new ArrayList<Horario>();
        listaHorarios.addAll(a.getHorarios());
        for(int i=0;i<horario.size();i++){
            for(int j=0;j<horario.get(i).size();j++){
                /*for(Horario h : a.getHorarios()){
                    for(Integer hora : h.getHoras()){
                        if(h.getDia()==(i+1) && hora==(j+1)){
                            horario.get(i).set(j,a.getNombre());
                        }
                    }
                }*/
                for(Horario h : a.getHorarios()){
                    if(h.getDia()==(i+1)){
                        for(Integer hora : h.getHoras()){
                            if(hora==(j+1)){
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
