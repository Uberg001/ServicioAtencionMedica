package com.example.atencionMedica;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AtencionMedicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtencionMedicaApplication.class, args);
	}

}