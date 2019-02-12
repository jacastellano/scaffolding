package com.scaffolding.spring.controler;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaffolding.spring.model.EntityModel;
import com.scaffolding.spring.persistence.repository.EntityRepository;

@RestController
public class EntityController {

	@Inject
	private EntityRepository repository;

	/**
	 * Get a specific entity
	 * 
	 * @param entityId
	 * @return the entity object that it was found
	 */
	@GetMapping("/entities/{entityId}")
	public ResponseEntity<EntityModel> findEntityById(@PathVariable Long entityId) {

		return repository.findById(entityId).map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Get all entities
	 * 
	 * @return a complete list of entities
	 */
	@GetMapping("/entities")
	public ResponseEntity<Iterable<EntityModel>> findAllEntities() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	/**
	 * Create a new entity
	 * 
	 * @param entity
	 * @return the entity object that it was created
	 */
	@PostMapping("/entities")
	public ResponseEntity<EntityModel> createEntity(@RequestBody EntityModel entity) {
		entity.setCreateDate(new Date());
		return new ResponseEntity<>(repository.save(entity), HttpStatus.CREATED);
	}

	/**
	 * Update an entity
	 * 
	 * @param entity an entity object with new values
	 * @param entityId
	 * @return the entity object that it was updated
	 */
	@PutMapping("/entities/{entityId}")
	public ResponseEntity<EntityModel> updateEntity(@RequestBody EntityModel entity, @PathVariable Long entityId) {

		return repository.findById(entityId).map(e -> {
			e.setEntityTitle(entity.getEntityTitle());
			e.setEntityDescription(entity.getEntityDescription());
			e.setUpdateDate(new Date());
			return new ResponseEntity<>(repository.save(e), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Delete an entity
	 * 
	 * @param entityId
	 */
	@DeleteMapping("/entities/{entityId}")
	public ResponseEntity<EntityModel> deleteEntity(@PathVariable Long entityId) {

		return repository.findById(entityId).map(e -> {
			e.setDeleteDate(new Date());
			return new ResponseEntity<>(repository.save(e), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
