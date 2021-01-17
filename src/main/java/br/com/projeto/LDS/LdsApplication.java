package br.com.projeto.LDS;

import br.com.projeto.LDS.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LdsApplication{
	@Autowired
	private S3Service service;

	public static void main(String[] args) {
		SpringApplication.run(LdsApplication.class, args);
	}

}
