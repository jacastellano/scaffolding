package com.scaffolding.spring.main.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaffolding.spring.main.domain.EntityDTO;
import com.scaffolding.spring.main.service.EntityService;

@RestController
public class EntityController {

	@Inject
	private EntityService service;

	/**
	 * Get an specific entity
	 * 
	 * @param entityId
	 * @return the entity object that it was found
	 */
	@GetMapping("/entities/{id}")
	public ResponseEntity<EntityDTO> findEntityById(@PathVariable Long id) {

		EntityDTO foundEntity = service.findEntityById(id);

		return (foundEntity != null) ? new ResponseEntity<>(foundEntity, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	/**
	 * Get all entities
	 * 
	 * @return a complete list of entities
	 */
	@GetMapping("/entities")
	public ResponseEntity<Iterable<EntityDTO>> findAllEntities() {
		return new ResponseEntity<>(service.findAllEntities(), HttpStatus.OK);
	}

	/**
	 * Create a new entity
	 * 
	 * @param entity
	 * @return the entity object that it was created
	 */
	@PostMapping("/entities")
	public ResponseEntity<EntityDTO> createEntity(@Valid @RequestBody EntityDTO entity) {
		return new ResponseEntity<>(service.createEntity(entity), HttpStatus.CREATED);
	}

	/**
	 * Update an entity
	 * 
	 * @param entity   an entity object with new values
	 * @param entityId
	 * @return the entity object that it was updated
	 */
	@PutMapping("/entities/{id}")
	public ResponseEntity<EntityDTO> updateEntity(@Valid @RequestBody EntityDTO entity, @PathVariable Long id) {

		EntityDTO updatedEntity = service.updateEntity(entity, id);

		return (updatedEntity != null) ? new ResponseEntity<>(updatedEntity, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	/**
	 * Delete an entity
	 * 
	 * @param entityId
	 */
	@DeleteMapping("/entities/{id}")
	public ResponseEntity<EntityDTO> deleteEntity(@PathVariable Long id) {

		EntityDTO deletedEntity = service.deleteEntity(id);

		return (deletedEntity != null) ? new ResponseEntity<>(deletedEntity, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
