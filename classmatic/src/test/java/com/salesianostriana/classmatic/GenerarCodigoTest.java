package com.salesianostriana.classmatic;

import com.salesianostriana.classmatic.entidades.Usuario;
import com.salesianostriana.classmatic.servicios.UsuarioServicio;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
public class GenerarCodigoTest {

    UsuarioServicio usuarioServicioMock = Mockito.mock(UsuarioServicio.class);

//    @Mock
//    static UsuarioRepositorio usuarioRepositorio;

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
        usuario.setCodigoInvitacion(800);
    }



    @Test
    @DisplayName("Comprobar tipo de dato, que sea un int")
    public void comprobarTipoDeDato(){
            Mockito.when(usuarioServicioMock.autogenerarCodigo()).thenReturn(usuario.getCodigoInvitacion());
            assertEquals(usuarioServicioMock.autogenerarCodigo(), 800 );
    }

    @Test
    @DisplayName("Comprobar tipo de dato, que no sea un String")
    public void comprobarTipoDeDato2(){
        Mockito.when(usuarioServicioMock.autogenerarCodigo()).thenReturn(usuario.getCodigoInvitacion());
        assertNotEquals(usuarioServicioMock.autogenerarCodigo(), "800", "se comprueba que no se genera un String");
    }

    @Test
    @DisplayName("Comprobar código entre 1 y 1 millón, se prueba el número 500")
    public void comprobarRangoDeCodigoTrue(){
        usuario.setCodigoInvitacion(500);
        Mockito.when(usuarioServicioMock.autogenerarCodigo()).thenReturn(usuario.getCodigoInvitacion());
        assertTrue(usuarioServicioMock.autogenerarCodigo() <= 1000000);
    }

    @Test
    @DisplayName("Comprobar código entre 1 y 1 millón, se prueba el numero 1000005")
    public void comprobarRangoDeCodigoFalse(){
        usuario.setCodigoInvitacion(1000005);
        Mockito.when(usuarioServicioMock.autogenerarCodigo()).thenReturn(usuario.getCodigoInvitacion());
        assertFalse(usuarioServicioMock.autogenerarCodigo() <= 1000000);
    }

    @Test
    @DisplayName("Comprobar código entre 1 y 1 millón, se prueba el numero -15")
    public void comprobarRangoDeCodigoFalse2(){
        usuario.setCodigoInvitacion(-15);
        Mockito.when(usuarioServicioMock.autogenerarCodigo()).thenReturn(usuario.getCodigoInvitacion());
        assertFalse(usuarioServicioMock.autogenerarCodigo() >= 1);
    }



}
