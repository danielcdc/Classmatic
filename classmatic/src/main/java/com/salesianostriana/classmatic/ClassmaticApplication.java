package com.salesianostriana.classmatic;

import com.salesianostriana.classmatic.entidades.*;
import com.salesianostriana.classmatic.servicios.AlumnoServicio;
import com.salesianostriana.classmatic.servicios.ProfesorServicio;
import com.salesianostriana.classmatic.servicios.UsuarioServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class ClassmaticApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassmaticApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(AlumnoServicio alumnoServicio, ProfesorServicio profesorServicio,PasswordEncoder passWordEncoder){
		return args -> {
			Alumno a1 = Alumno.builder()
					.nombre("Carlos")
					.apellidos("Pérez Ruiz")
					.email("caperu@gmail.com")
					.passdword(passWordEncoder.encode("1234"))
					.fechaNacimiento(LocalDateTime.of(1995,3,13,3,15))
					.build();

			alumnoServicio.save(a1);


			Profesor p1 = Profesor.builder()
					.nombre("Alfonso")
					.apellidos("Ramos Roldán")
					.email("alraro@gmail.com")
					.passdword(passWordEncoder.encode("1234"))
					.fechaNacimiento(LocalDateTime.of(1987,8,23,17,0))
					.esJefe(false)
					.build();

			profesorServicio.save(p1);

			Profesor p2 = Profesor.builder()
					.nombre("Jose")
					.apellidos("Lago Vidal")
					.email("jolavi@gmail.com")
					.passdword(passWordEncoder.encode("admin"))
					.fechaNacimiento(LocalDateTime.of(1983,2,8,20,30))
					.esJefe(true)
					.build();
			profesorServicio.save(p2);

		};

	}

}
