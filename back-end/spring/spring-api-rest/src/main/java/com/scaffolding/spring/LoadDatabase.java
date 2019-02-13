package com.scaffolding.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scaffolding.spring.main.persistence.domain.EntityDAO;
import com.scaffolding.spring.main.persistence.repository.EntityRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDatabase {

	private static final String PRELOADING = "Preloading ";

	@Bean
	CommandLineRunner initDatabase(EntityRepository repository) {
		return args -> {
			log.info(PRELOADING + repository.save(new EntityDAO("EntityA", "Preloaded entity A")));
			log.info(PRELOADING + repository.save(new EntityDAO("EntityB", "Preloaded entity B")));
			log.info(PRELOADING + repository.save(new EntityDAO("EntityC", "Preloaded entity C")));
		};
	}
}
