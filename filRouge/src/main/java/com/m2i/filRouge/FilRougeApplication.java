package com.m2i.filRouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilRougeApplication {

	public static void main(String[] args) {
		// add profile oracle après
		System.setProperty("spring.profiles.active", "init");
		SpringApplication.run(FilRougeApplication.class, args);
		System.out.println("http://localhost:8080/filRouge");
	}

}
