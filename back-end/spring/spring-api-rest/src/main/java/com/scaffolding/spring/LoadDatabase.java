package com.scaffolding.spring;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scaffolding.spring.model.EntityModel;
import com.scaffolding.spring.persistence.repository.EntityRepository;

@Configuration
@Slf4j
class LoadDatabase {

	private static final String PRELOADING = "Preloading ";

	@Bean
	CommandLineRunner initDatabase(EntityRepository repository) {
		return args -> {
			log.info(PRELOADING + repository.save(new EntityModel("EntityA", "Preloaded entity A")));
			log.info(PRELOADING + repository.save(new EntityModel("EntityB", "Preloaded entity B")));
			log.info(PRELOADING + repository.save(new EntityModel("EntityC", "Preloaded entity C")));
		};
	}
}
