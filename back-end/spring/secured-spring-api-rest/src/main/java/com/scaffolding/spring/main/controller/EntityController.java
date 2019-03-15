package com.scaffolding.spring.main.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaffolding.spring.main.domain.EntityDTO;
import com.scaffolding.spring.main.service.EntityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Entity Controller")
@RestController
public class EntityController {

	@Inject
	private EntityService service;

	/**
	 * Find entity by id
	 * 
	 * @param id Entity id
	 * @return the entity object that it was found. If no entity was found it
	 *         returns status not found (404)
	 */
	@ApiOperation(value = "findEntityById", notes = "Find entity by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Entity found."),
			@ApiResponse(code = 404, message = "The entity you were trying to reach is not found") })
	@PreAuthorize("#oauth2.hasScope('read')")
	@GetMapping("/entities/{id}")
	public ResponseEntity<EntityDTO> findEntityById(@PathVariable Long id) {

		EntityDTO foundEntity = service.findEntityById(id);

		return (foundEntity != null) ? new ResponseEntity<>(foundEntity, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	/**
	 * Find all entities
	 * 
	 * @return a complete list of entities. If no entity was found it returns an
	 *         empty collection
	 */
	@ApiOperation(value = "findAllEntities", notes = "Find all entities")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Entities found.") })
	@PreAuthorize("#oauth2.hasScope('read')")
	@GetMapping("/entities")
	public ResponseEntity<List<EntityDTO>> findAllEntities() {
		return new ResponseEntity<>(service.findAllEntities(), HttpStatus.OK);
	}

	/**
	 * Create a new entity
	 * 
	 * @param entity
	 * @return the entity object that it was created
	 */
	@ApiOperation(value = "createEntity", notes = "Create a new entity")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully new entity added.") })
	@PreAuthorize("#oauth2.hasScope('write')")
	@PostMapping("/entities")
	public ResponseEntity<EntityDTO> createEntity(@Valid @RequestBody EntityDTO entity) {
		return new ResponseEntity<>(service.createEntity(entity), HttpStatus.CREATED);
	}

	/**
	 * Update an entity
	 * 
	 * @param entity an entity object with new values
	 * @param id     Entity id
	 * @return the entity object that it was updated. If no entity was found it
	 *         returns status not found (404)
	 */
	@ApiOperation(value = "updateEntity", notes = "Update an entity")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update entity."),
			@ApiResponse(code = 404, message = "The resource you were trying to update is not found") })
	@PreAuthorize("#oauth2.hasScope('write')")
	@PutMapping("/entities/{id}")
	public ResponseEntity<EntityDTO> updateEntity(@Valid @RequestBody EntityDTO entity, @PathVariable Long id) {

		EntityDTO updatedEntity = service.updateEntity(entity, id);

		return (updatedEntity != null) ? new ResponseEntity<>(updatedEntity, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	/**
	 * Delete an entity
	 * 
	 * @param id Entity id
	 * @return the entity object that it was deleted. If no entity was found it
	 *         returns status not found (404)
	 */
	@ApiOperation(value = "deleteEntity", notes = "Delete an entity")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully delete entity."),
			@ApiResponse(code = 404, message = "The resource you were trying to delete is not found") })
	@PreAuthorize("#oauth2.hasScope('trust')")
	@DeleteMapping("/entities/{id}")
	public ResponseEntity<EntityDTO> deleteEntity(@PathVariable Long id) {

		EntityDTO deletedEntity = service.deleteEntity(id);

		return (deletedEntity != null) ? new ResponseEntity<>(deletedEntity, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
