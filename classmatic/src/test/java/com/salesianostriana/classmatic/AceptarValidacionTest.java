package com.salesianostriana.classmatic;

import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.repositorios.UsuarioRepositorio;
import com.salesianostriana.classmatic.servicios.UsuarioServicio;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class AceptarValidacionTest {

    /*
    - Código incorrecto
    - Lista vacia
     */

    UsuarioServicio usuarioServicioMock = Mockito.mock(UsuarioServicio.class);

    @Mock
    static UsuarioRepositorio usuarioRepositorio;

    @InjectMocks
    static Usuario usuario;

    @BeforeAll
    public static void inicializando(){
        usuario = new Usuario() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }
        };
        usuario.setCodigoInvitacion(1000);
        usuario.setId(123456);

    }


    @Test
    @DisplayName("Comprobar que el código coincide")
    public void CodigoCorrecto(){
        Mockito.when(usuarioServicioMock.findById(usuario.getId())).thenReturn(usuario);
        assertEquals(usuario.getCodigoInvitacion(), 1000);

    }

    @Test
    @DisplayName("Comprobar que el falle si el codigo no coincide")
    public void CodigoCorrecto2(){
        Mockito.when(usuarioServicioMock.findById(usuario.getId())).thenReturn(usuario);
        assertNotEquals(usuario.getCodigoInvitacion(), 350);

    }

    @Test
    @DisplayName("Comprobar listaVacia")
    public void listaVacia(){
        List<Usuario> lista = new ArrayList<Usuario>();
        //lista.add(usuario);
        Mockito.when(usuarioServicioMock.findById(usuario.getId())).thenReturn(usuario);
        assertNotEquals(usuarioServicioMock.findById(usuario.getId()), lista);

    }
}
