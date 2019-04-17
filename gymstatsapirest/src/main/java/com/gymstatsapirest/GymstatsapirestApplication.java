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
public class GymstatsapirestApplication implements CommandLineRunner
{
	@Autowired
	GeneroRepository generoRepository;
	public static void main(String[] args) 
	{
		SpringApplication.run(GymstatsapirestApplication.class, args);
		System.out.println("Hello world");
	}
	@Override
	public void run(String... args) throws Exception 
	{
	}
}
