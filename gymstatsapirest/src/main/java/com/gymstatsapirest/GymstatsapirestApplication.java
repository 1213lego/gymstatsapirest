package com.gymstatsapirest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
