package com.scaffolding.spring.load;

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
			log.info(PRELOADING + repository.save(new EntityDAO("0001/2019", "Entity A", "Entity resume A...", 1L)));
			log.info(PRELOADING + repository.save(new EntityDAO("0002/2019", "Entity B", "Entity resume B...", 1L)));
			log.info(PRELOADING + repository.save(new EntityDAO("0003/2019", "Entity C", "Entity resume C...", 1L)));
		};
	}
}
