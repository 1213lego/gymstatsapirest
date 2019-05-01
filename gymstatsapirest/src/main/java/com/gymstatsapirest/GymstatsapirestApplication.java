package com.gymstatsapirest;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.repository.AsistenciaUsuarioRepository;
import com.gymstatsapirest.repository.SuscripcionesRepository;
import com.gymstatsapirest.repository.TarifaRepository;
import com.gymstatsapirest.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//spring.jpa.hibernate.ddl-auto=create
@SpringBootApplication()
@EnableSwagger2
public class GymstatsapirestApplication implements CommandLineRunner
{
	public static void main(String[] args) 
	{
		SpringApplication.run(GymstatsapirestApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception 
	{
	}
}
