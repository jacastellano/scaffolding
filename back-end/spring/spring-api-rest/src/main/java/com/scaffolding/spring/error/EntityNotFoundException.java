package com.scaffolding.spring.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Long id) {
		super("Could not find entity " + id);
		log.info("Could not find entity " + id);
	}

}
