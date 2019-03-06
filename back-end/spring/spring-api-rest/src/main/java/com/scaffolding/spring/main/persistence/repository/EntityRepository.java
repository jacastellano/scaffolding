package com.scaffolding.spring.main.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scaffolding.spring.main.persistence.domain.EntityDAO;

public interface EntityRepository extends JpaRepository<EntityDAO, Long> {

	@Query("SELECT e FROM EntityDAO e WHERE e.deleteDate IS NULL")
	List<EntityDAO> findAll();
	
	@Query("SELECT e FROM EntityDAO e WHERE e.entityId =?1 AND e.deleteDate IS NULL")
	Optional<EntityDAO> findById(Long id);

}