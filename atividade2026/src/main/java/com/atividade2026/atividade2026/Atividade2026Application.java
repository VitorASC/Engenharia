package com.atividade2026.atividade2026;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.atividade2026.atividade2026")
@EntityScan(basePackages = "com.atividade2026.atividade2026.domains")
@EnableJpaRepositories(basePackages = "com.atividade2026.atividade2026.repositories")
@SpringBootApplication
public class Atividade2026Application {

	public static void main(String[] args) {
		SpringApplication.run(Atividade2026Application.class, args);
	}

}
