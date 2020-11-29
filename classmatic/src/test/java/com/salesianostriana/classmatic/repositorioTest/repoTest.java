package com.salesianostriana.classmatic.repositorioTest;

import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.repositorios.UsuarioRepositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class repoTest {


    UsuarioRepositorio usuarioRepositorioMock = Mockito.mock(UsuarioRepositorio.class);

    /*@Mock
    UsuarioRepositorio usuarioRepositorioMock;*/
    //Por alguna razón, si utilizo esta anotación, los tests me devuelven nullPointerException

    @Test
    public void findByEmail(){
        Usuario u1 = new Usuario() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }
        };

        u1.setEmail("estoesuncoreo@gmail.com");

        when(usuarioRepositorioMock.findFirstByEmail("estoesuncoreo@gmail.com")).thenReturn(Optional.of(u1));

    }

    @Test
    public void findById(){
        Usuario u1 = new Usuario() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }
        };


        when(usuarioRepositorioMock.findById(u1.getId())).thenReturn(Optional.of(u1));
    }

    @Test
    public void findByAll(){
        Usuario u1 = new Usuario() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }
        };

        Usuario u2 = new Usuario() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }
        };

        List <Usuario> listado = new ArrayList<>();
        listado.add(u1);
        listado.add(u2);

        when(usuarioRepositorioMock.findAll()).thenReturn(listado);

        System.out.println(listado);
        //Por alguna razón, los dos usuarios creados tienen el mismo id, es probable que haya un fallo con el @GeneratedValue de la entidad Usuario
    }
}
