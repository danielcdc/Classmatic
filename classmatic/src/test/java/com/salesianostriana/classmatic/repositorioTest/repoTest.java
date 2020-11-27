package com.salesianostriana.classmatic.repositorioTest;

import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.repositorios.UsuarioRepositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class repoTest {

    UsuarioRepositorio usuarioRepositorioMock = Mockito.mock(UsuarioRepositorio.class);

    @Test
    public void email(){
        Usuario u1 = new Usuario() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }
        };

        u1.setEmail("estoesuncoreo@gmail.com");

        when(usuarioRepositorioMock.findFirstByEmail("estoesuncoreo@gmail.com")).thenReturn(Optional.of(u1));

    }

    
}
