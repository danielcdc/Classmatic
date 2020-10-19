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

import java.time.LocalDate;
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
			/*Alumno a1 = Alumno.builder()
					.nombre("Carlos")
					.apellidos("Pérez Ruiz")
					.email("caperu@gmail.com")
					.passdword(passWordEncoder.encode("1234"))
					.fechaNacimiento(LocalDateTime.of(1995,3,13,3,15))
					.build();*/
			Alumno a1 =new Alumno();
			a1.setNombre("Carlos");
			a1.setApellidos("Pérez Ruiz");
			a1.setEmail("caperu@gmail.com");
			a1.setPassdword(passWordEncoder.encode("1234"));
			a1.setFechaNacimiento(LocalDate/*Time*/.of(1995,3,13/*,3,15*/));
			/*a1.setAsignaturas(new ArrayList<Asignatura>());
			a1.setSituacionesExc(new ArrayList<SituacionExcepcional>());
			a1.setSolicitudesAmp(new ArrayList<SolicitudAmpliacionMatricula>());
			a1.setCurso(new Curso());*/
			alumnoServicio.save(a1);

			Alumno a2 =new Alumno();
			a2.setNombre("Mario");
			a2.setApellidos("Angulo Ruiz");
			a2.setEmail("maanru@gmail.com");
			a2.setPassdword(passWordEncoder.encode("1234"));
			a2.setFechaNacimiento(LocalDate/*Time*/.of(1995,3,13/*,3,15*/));
			/*a2.setAsignaturas(new ArrayList<Asignatura>());
			a2.setSituacionesExc(new ArrayList<SituacionExcepcional>());
			a2.setSolicitudesAmp(new ArrayList<SolicitudAmpliacionMatricula>());
			a2.setCurso(new Curso());*/
			alumnoServicio.save(a2);

			Alumno a3 =new Alumno();
			a3.setNombre("Rodrigo");
			a3.setApellidos("Lozano Martín");
			a3.setEmail("roloma@gmail.com");
			a3.setPassdword(passWordEncoder.encode("1234"));
			a3.setFechaNacimiento(LocalDate/*Time*/.of(1995,3,13/*,3,15*/));
			/*a3.setAsignaturas(new ArrayList<Asignatura>());
			a3.setSituacionesExc(new ArrayList<SituacionExcepcional>());
			a3.setSolicitudesAmp(new ArrayList<SolicitudAmpliacionMatricula>());
			a3.setCurso(new Curso());*/
			alumnoServicio.save(a3);


			/*Profesor p1 = Profesor.builder()
					.nombre("Alfonso")
					.apellidos("Ramos Roldán")
					.email("alraro@gmail.com")
					.passdword(passWordEncoder.encode("1234"))
					.fechaNacimiento(LocalDateTime.of(1987,8,23,17,0))
					.esJefe(false)
					.build();*/
			Profesor p1=new Profesor();
			p1.setNombre("Alfonso");
			p1.setApellidos("Ramos Roldán");
			p1.setEmail("alraro@gmail.com");
			p1.setFechaNacimiento(LocalDate/*Time*/.of(1987,8,23/*,17,0*/));
			p1.setPassdword(passWordEncoder.encode("1234"));
			p1.setEsJefe(false);
			profesorServicio.save(p1);

			Profesor p3=new Profesor();
			p3.setNombre("Raul");
			p3.setApellidos("Roldán Etena");
			p3.setEmail("raroet@gmail.com");
			p3.setFechaNacimiento(LocalDate/*Time*/.of(1987,8,23/*,17,0*/));
			p3.setPassdword(passWordEncoder.encode("1234"));
			p3.setEsJefe(false);
			profesorServicio.save(p3);

			Profesor p4=new Profesor();
			p4.setNombre("Lucas");
			p4.setApellidos("Martín Romero");
			p4.setEmail("lumaro@gmail.com");
			p4.setFechaNacimiento(LocalDate/*Time*/.of(1987,8,23/*,17,0*/));
			p4.setPassdword(passWordEncoder.encode("1234"));
			p4.setEsJefe(false);
			profesorServicio.save(p4);


			Profesor p2=new Profesor();
			p2.setNombre("Jose");
			p2.setApellidos("Lago Vidal");
			p2.setEmail("admin");
			p2.setFechaNacimiento(LocalDate/*Time*/.of(1983,2,8/*,20,30*/));
			p2.setPassdword(passWordEncoder.encode("admin"));
			p2.setEsJefe(true);
			profesorServicio.save(p2);

			Profesor p5=new Profesor();
			p5.setNombre("Jesús");
			p5.setApellidos("Lopera Antúnez");
			p5.setEmail("jeloan@gmail.com");
			p5.setFechaNacimiento(LocalDate/*Time*/.of(1983,2,8/*,20,30*/));
			p5.setPassdword(passWordEncoder.encode("admin"));
			p5.setEsJefe(true);
			profesorServicio.save(p5);

		};

	}

}
