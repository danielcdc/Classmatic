package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.salesianostriana.classmatic.repositorios.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class UsuarioServicio extends ServicioBaseImp <Usuario, Long, UsuarioRepositorio> {

    private final UsuarioRepositorio usuarioRepositorio;



    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepositorio.findFirstByEmail(email);
    }


    public int autogenerarCodigo(){
        Integer codigo;
        boolean rechazado=true;
        List<Integer> usados=new ArrayList<Integer>();
        for(Usuario us : findAll()){
            usados.add(us.getCodigoInvitacion());
        }

        do {
            codigo = (int) Math.floor(Math.random() * 1000000 + 1);
            if(!usados.contains(codigo)){
                rechazado=false;
            }

        }while(rechazado);
        return codigo;
    }


}
