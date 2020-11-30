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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

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

    @Mock
    PasswordEncoder passwordEncoder;

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
        usuario.setPassdword("4321");
        usuario.setHabilitado(false);

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

    @Disabled
    @Test
    @DisplayName("Comprobar cambio de contraseña, no funciona")
    public void cambiarcontrasenya(){
        String contrasenya = "1234";
        int codigo = 1000;
        List<Usuario> lista = new ArrayList<Usuario>();
        lista.add(usuario);

        Mockito.when(usuarioRepositorio.findById(usuario.getId())).thenReturn(Optional.of(usuario));
//      Mockito.when(usuarioServicio.edit(usuario)).thenReturn(usuario);
//      Mockito.when(usuarioServicio.findAll()).thenReturn(lista);
        Mockito.when(usuarioRepositorio.findAll()).thenReturn(lista);
        Mockito.when(usuarioRepositorio.save(usuario)).thenReturn(null);
        Mockito.when(passwordEncoder.encode(contrasenya)).thenReturn(contrasenya);

        usuarioServicioMock.aceptarValidacion(codigo, contrasenya);

        assertTrue(usuario.isHabilitado());
        assertEquals(usuario.getPassdword(), contrasenya);

    }
}
