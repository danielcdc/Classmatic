package com.salesianostriana.classmatic.servicios;

import com.salesianostriana.classmatic.entidades.Alumno;
import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.servicios.base.ServicioBaseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.salesianostriana.classmatic.repositorios.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class UsuarioServicio extends ServicioBaseImp <Usuario, Long, UsuarioRepositorio> {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    PasswordEncoder passWordEncoder;

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepositorio.findFirstByEmail(email);
    }


    public int autogenerarCodigo(){
        int codigo;
        boolean rechazado=true;
        List<Integer> usados=new ArrayList<>();
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

    public void  aceptarValidacion(int codigo, String contrasenya){
        List<Usuario>listaU=findAll();
        boolean  parar=false;
        //boolean verificado=false;
        /*for(Usuario usu : listaU){
            if(usu.getCodigoInvitacion() == codigo){
                usu.setHabilitado(true);
                usu.setPassdword(passWordEncoder.encode(contrasenya));
            }
        }*/
        for(int i=0;i<listaU.size()&&!parar;i++){
            if(listaU.get(i).getCodigoInvitacion() == codigo){
                listaU.get(i).setHabilitado(true);
                System.out.println("La contrasenya es......................."+contrasenya);
                listaU.get(i).setPassdword(passWordEncoder.encode(contrasenya));
                listaU.get(i).setHabilitado(true);
                edit(listaU.get(i));
                parar=true;
                //verificado=true;
            }
        }
        //return verificado;
    }


}
