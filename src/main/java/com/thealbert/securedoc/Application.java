package com.thealbert.securedoc;

import com.thealbert.securedoc.domain.RequestContext;
import com.thealbert.securedoc.entity.RoleEntity;
import com.thealbert.securedoc.enumeration.Authority;
import com.thealbert.securedoc.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
		return args -> {
			RequestContext.setUserId(0L);
			var userRole = new RoleEntity();
			userRole.setName(Authority.USER.name());
			userRole.setAuthorities(Authority.USER);
			roleRepository.save(userRole);

			var adminRole = new RoleEntity();
			adminRole.setName(Authority.ADMIN.name());
			adminRole.setAuthorities(Authority.ADMIN);
			roleRepository.save(adminRole);
			RequestContext.start();
		};
	}
}
