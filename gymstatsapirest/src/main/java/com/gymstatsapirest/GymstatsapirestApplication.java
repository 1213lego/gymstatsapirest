package com.gymstatsapirest;
import com.gymstatsapirest.model.AsistenciasUsuario;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.repository.AsistenciaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//spring.jpa.hibernate.ddl-auto=create
@SpringBootApplication()
@EnableSwagger2
public class GymstatsapirestApplication implements CommandLineRunner
{
	@Autowired
	AsistenciaUsuarioRepository asistenciaUsuarioRepository;
	public static void main(String[] args) 
	{
		SpringApplication.run(GymstatsapirestApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception 
	{
	}
}
