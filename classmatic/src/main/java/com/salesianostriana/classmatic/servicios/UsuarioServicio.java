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

    /**
     * Genera un código de invitación de forma "aleatoria" y lo devuelve.
     * @return Un número entero "aleatorio"
     */
    public int autogenerarCodigo() {
        int codigo;
        boolean rechazado = true;
        List<Integer> usados = new ArrayList<>();
        // Guarda todos los códigos de invitación generados hasta el momento.
        for (Usuario us : findAll()) {
            usados.add(us.getCodigoInvitacion());
        }
        // Genera un código aleatorio entre 1 y un millón. Si está repetido,
        // vuelve a generar otro número aleatorio.
        do {
            codigo = (int) Math.floor(Math.random() * 1000000 + 1);
            if (!usados.contains(codigo)) {
                rechazado = false;
            }
        } while (rechazado);
        return codigo;
    }

    /**
     * Obtiene una lista de todos los alumnos y la recorre, buscando al usuario que tenga la clave proporcionada por
     * parámetro, habilitándolo para usar la aplicación.
     * @param codigo El código enviado por correo.
     * @param contrasenya La nueva contraseña introducida por el usuario.
     */
    public void aceptarValidacion(int codigo, String contrasenya) {
        List<Usuario> listaU = findAll();
        boolean parar = false;
        for (int i = 0; i < listaU.size() && !parar; i++) {
            if (listaU.get(i).getCodigoInvitacion() == codigo) {
                System.out.println("La contraseña es......................." + contrasenya);
                listaU.get(i).setPassdword(passWordEncoder.encode(contrasenya));
                listaU.get(i).setHabilitado(true);
                edit(listaU.get(i));
                parar = true;
            }
        }
    }


}
