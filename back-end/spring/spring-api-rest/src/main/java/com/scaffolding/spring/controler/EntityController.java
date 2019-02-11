package com.scaffolding.spring.controler;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaffolding.spring.error.EntityNotFoundException;
import com.scaffolding.spring.model.EntityModel;
import com.scaffolding.spring.persistence.repository.EntityRepository;

@RestController
public class EntityController {

	private final EntityRepository repository;

	public EntityController(EntityRepository repository) {
		this.repository = repository;
	}

	/**
	 * Get all entities
	 * 
	 * @return a complete list of entities
	 */
	@GetMapping("/entities")
	public List<EntityModel> all() {
		return repository.findAll();
	}

	/**
	 * Create a new entity
	 * 
	 * @param newEntity
	 * @return the entity object that it was created
	 */
	@PostMapping("/entities")
	public EntityModel newEntity(@RequestBody EntityModel newEntity) {
		newEntity.setCreateDate(new Date());
		return repository.save(newEntity);
	}

	/**
	 * Get a specific entity
	 * 
	 * @param entityId
	 * @return the entity object that it was found
	 */
	@GetMapping("/entities/{entityId}")
	public EntityModel one(@PathVariable Long entityId) {
		return repository.findById(entityId).orElseThrow(() -> new EntityNotFoundException(entityId));
	}

	/**
	 * Update an entity
	 * 
	 * @param newEntity an entity object with new values
	 * @param entityId
	 * @return the entity object that it was updated
	 */
	@PutMapping("/entities/{entityId}")
	public EntityModel replaceEntity(@RequestBody EntityModel newEntity, @PathVariable Long entityId) {

		return repository.findById(entityId).map(entity -> {
			entity.setEntityTitle(newEntity.getEntityTitle());
			entity.setEntityDescription(newEntity.getEntityDescription());
			entity.setUpdateDate(new Date());
			return repository.save(entity);
		}).orElseGet(() -> {
			newEntity.setEntityId(entityId);
			newEntity.setCreateDate(new Date());
			return repository.save(newEntity);
		});
	}

	/**
	 * Delete an entity
	 * 
	 * @param entityId
	 */
	@DeleteMapping("/entities/{entityId}")
	public void deleteEntity(@PathVariable Long entityId) {
		repository.deleteById(entityId);
	}

}
