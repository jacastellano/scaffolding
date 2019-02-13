package com.scaffolding.spring.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.scaffolding.spring.main.domain.EntityDTO;
import com.scaffolding.spring.main.persistence.domain.EntityDAO;
import com.scaffolding.spring.main.persistence.repository.EntityRepository;
import com.scaffolding.spring.mapper.EntityMapper;

@Service
public class EntityService {

	@Inject
	private EntityRepository repository;

	/**
	 * Get a specific entity
	 * 
	 * @param entityId
	 * @return the entity object that it was found
	 */
	public EntityDTO findEntityById(Long entityId) {
		return repository.findById(entityId).map(EntityMapper::convert).orElseGet(() -> null);

	}

	/**
	 * Get all entities
	 * 
	 * @return a complete list of entities
	 */
	public List<EntityDTO> findAllEntities() {
		List<EntityDAO> entityList = repository.findAll();
		return entityList.stream().map(EntityMapper::convert).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Create a new entity
	 * 
	 * @param entity
	 * @return the entity object that it was created
	 */
	public EntityDTO createEntity(EntityDTO entityDTO) {
		entityDTO.setCreateDate(new Date());
		EntityDAO entityDAO = EntityMapper.convert(entityDTO);
		return EntityMapper.convert(repository.save(entityDAO));
	}

	/**
	 * Update an entity
	 * 
	 * @param entity   an entity object with new values
	 * @param entityId
	 * @return the entity object that it was updated
	 */
	public EntityDTO updateEntity(EntityDTO entity, Long entityId) {

		EntityDAO entityToSave = repository.findById(entityId).map(e -> {
			e.setEntityTitle(entity.getEntityTitle());
			e.setEntityDescription(entity.getEntityDescription());
			e.setUpdateDate(new Date());
			return e;

		}).orElseGet(() -> null);

		return (entityToSave != null) ? EntityMapper.convert(repository.save(entityToSave)) : null;
	}

	/**
	 * Delete an entity
	 * 
	 * @param entityId
	 */
	public EntityDTO deleteEntity(Long entityId) {

		EntityDAO entityToSave = repository.findById(entityId).map(e -> {
			e.setDeleteDate(new Date());
			return e;

		}).orElseGet(() -> null);

		return (entityToSave != null) ? EntityMapper.convert(repository.save(entityToSave)) : null;

	}

}