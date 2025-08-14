package com.aluracursos.literatura_challenge;

import com.aluracursos.literatura_challenge.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaChallengeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.menuPrincipal();
	}
}
