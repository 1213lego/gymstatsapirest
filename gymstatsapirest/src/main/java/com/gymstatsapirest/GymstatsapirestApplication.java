package com.gymstatsapirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gymstatsapirest.model.Genero;
import com.gymstatsapirest.repository.GeneroRepository;
//spring.jpa.hibernate.ddl-auto=create
@SpringBootApplication
public class GymstatsapirestApplication implements CommandLineRunner{

	@Autowired
	GeneroRepository generoRepository;
	public static void main(String[] args) {
		SpringApplication.run(GymstatsapirestApplication.class, args);
		System.out.println("Hello world");
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*Genero genero=new Genero();
		genero.setId(new Short((short)1));
		genero.setGenero("F");
		System.out.println("ingreso " + generoRepository.save(genero));
		Genero genero1=new Genero();
		genero1.setId(new Short((short)2));
		genero1.setGenero("M");
		System.out.println("ingreso " + generoRepository.save(genero1));*/
	}
}
