package com.example.practica_seguridad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PracticaSeguridadApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaSeguridadApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("123"));

	}

}
